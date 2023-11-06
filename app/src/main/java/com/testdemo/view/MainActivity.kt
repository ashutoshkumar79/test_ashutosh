package com.testdemo.view

import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.testdemo.R
import com.testdemo.databinding.ActivityMainBinding
import com.testdemo.view.base.BaseActivity
import com.testdemo.view.login.LoginFragment

class MainActivity : BaseActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val navController = findNavController(R.id.nav_host_fragment)
        navController.navigateUp() // to clear previous navigation history
        navController.navigate(R.id.loginFragment)

//        val fragment = LoginFragment()
//        supportFragmentManager.beginTransaction()
//            .replace(mBinding.navHostFragment.id, fragment)
//            .commitAllowingStateLoss()

    }
}