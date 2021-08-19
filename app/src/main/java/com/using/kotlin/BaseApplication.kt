package com.using.kotlin

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

open class BaseApplication : Application() {

    companion object {
        private var baseApplication: BaseApplication? = null

        fun getContext(): Context {
            return baseApplication!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        baseApplication = this
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}