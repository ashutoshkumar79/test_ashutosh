package com.testdemo.data.preference

open class SPSingletonHolder<out T, in A>(creator:(A) -> T) {

    private var creator: ((A) -> T)? = creator

    private var instance: T? = null

    fun getInstance(args: A): T {
        val i = instance
        if(i!=null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if(i2!=null) {
                i2
            } else {
                val created = creator!!(args)
                instance = created
                creator = null
                created
            }
        }

    }
}