package com.using.kotlin

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.using.kotlin.base.BaseActivity
import com.using.kotlin.data.remote.picasso.PicassoImageLoader
import com.using.kotlin.data.remote.picasso.PicassoImageLoadingListener
import com.using.kotlin.model.Android

class DetailActivity : BaseActivity() {

    companion object {
        private val TAG: String = DetailActivity::class.java.simpleName
    }

    private lateinit var imageLoadingProgressBar: ProgressBar
    private lateinit var logoImageView: ImageView
    private lateinit var codeNameTextView: TextView
    private lateinit var versionNumbersTextView: TextView
    private lateinit var apiLevelTextView: TextView
    private lateinit var releaseDateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_detail
    }

    override fun initializeView() {
        imageLoadingProgressBar = findViewById(R.id.imageLoadingProgressBar)
        logoImageView           = findViewById(R.id.logoImageView)
        codeNameTextView        = findViewById(R.id.codeNameTextView)
        versionNumbersTextView  = findViewById(R.id.versionNumbersTextView)
        apiLevelTextView        = findViewById(R.id.apiLevelTextView)
        releaseDateTextView     = findViewById(R.id.releaseDateTextView)
    }

    override fun initializeObject() {
        if (intent != null && intent.hasExtra("parcelable_android_key")) {
            val androidParcelable = intent.getParcelableExtra<Parcelable>("parcelable_android_key") as Android?

            PicassoImageLoader.load(
                this,
                androidParcelable!!.logo,
                R.drawable.user_placeholder,
                R.drawable.error_placeholder,
                logoImageView,
                object : PicassoImageLoadingListener {
                    override fun imageLoadSuccess() {
                        imageLoadingProgressBar.visibility = View.GONE
                    }

                    override fun imageLoadError(exception: Exception?) {
                        Toast.makeText(applicationContext, "An error occurred", Toast.LENGTH_SHORT).show()
                        imageLoadingProgressBar.visibility = View.GONE
                    }
                })

            codeNameTextView.text = androidParcelable.codeName
            versionNumbersTextView.text = androidParcelable.versionNumbers
            apiLevelTextView.text = androidParcelable.apiLevel
            releaseDateTextView.text = androidParcelable.releaseDate
        }
    }

    override fun initializeToolBar() {
    }

    override fun initializeCallbackListener() {
    }

    override fun addTextChangedListener() {
    }

    override fun setOnClickListener() {
    }
}