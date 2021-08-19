package com.using.kotlin.base

import android.os.Bundle
import android.util.Log
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        private val TAG = BaseActivity::class.java.simpleName
    }
    /*
     ***********************************************************************************************
     ********************************* BaseActivity abstract methods *******************************
     ***********************************************************************************************
     */
    @LayoutRes
    protected abstract fun getLayoutID(): Int
    protected abstract fun initializeView()
    protected abstract fun initializeObject()
    protected abstract fun initializeToolBar()
    protected abstract fun initializeCallbackListener()
    protected abstract fun addTextChangedListener()
    protected abstract fun setOnClickListener()
    /*
     ***********************************************************************************************
     ********************************* Activity lifecycle methods **********************************
     ***********************************************************************************************
     */
    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate(Bundle savedInstanceState)")

        setContentView(getLayoutID())

        initializeView()
        initializeObject()
        initializeToolBar()
        initializeCallbackListener()
        addTextChangedListener()
        setOnClickListener()
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart()")
    }

    @CallSuper
    override fun onRestart() { /* Only called after onStop() */
        super.onRestart()
        Log.i(TAG, "onRestart()")
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume()")
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause()")
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop()")
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy()")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.i(TAG, "onBackPressed()")
    }
}