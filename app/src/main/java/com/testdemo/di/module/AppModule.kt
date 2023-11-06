package com.testdemo.di.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.testdemo.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import com.testdemo.BuildConfig
import com.testdemo.data.api.ApiService
import com.testdemo.data.repository.AppRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    companion object{
        private const val CONNECT_TIME_OUT = 20L
        private const val WRITE_TIME_OUT = 30L
        private const val READ_TIME_OUT = 30L

        private const val NAMED_HEADER = "headerInterceptor"
        private const val NAMED_LOGGING = "loggingInterceptor"

    }

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    @Named(value = NAMED_HEADER)
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            chain.proceed(chain.request().newBuilder().apply {
                header("Context-Type", "application/json")
            }.build())
        }
    }

    @Provides
    @Singleton
    @Named(value = NAMED_LOGGING)
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if(BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@Named(NAMED_LOGGING) loggingInterceptor: HttpLoggingInterceptor, @Named(
        NAMED_HEADER) headerInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)

            addInterceptor(loggingInterceptor)
            addInterceptor(headerInterceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String, appGson: Gson): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    @Provides
    @Singleton
    fun provideAppRepository(apiService: ApiService) = AppRepository(apiService)


    @Provides
    @Singleton
    @Named("AppContext")
    fun provideContext(@ApplicationContext appContext: Context) = appContext


}