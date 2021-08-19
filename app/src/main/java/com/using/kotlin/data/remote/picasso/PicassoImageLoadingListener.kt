package com.using.kotlin.data.remote.picasso

interface PicassoImageLoadingListener {
    fun imageLoadSuccess()
    fun imageLoadError(exception: Exception?)
}