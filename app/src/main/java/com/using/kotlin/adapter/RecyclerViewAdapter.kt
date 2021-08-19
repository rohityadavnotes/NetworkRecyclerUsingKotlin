package com.using.kotlin.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.using.kotlin.model.Android
import com.using.kotlin.R
import com.using.kotlin.adapter.RecyclerViewAdapter.ItemViewHolder
import com.using.kotlin.data.remote.glide.GlideCacheUtil
import com.using.kotlin.data.remote.glide.GlideImageLoader
import com.using.kotlin.data.remote.glide.GlideImageLoadingListener
import com.using.kotlin.data.remote.picasso.PicassoImageLoader
import com.using.kotlin.data.remote.picasso.PicassoImageLoadingListener
import java.util.*

class RecyclerViewAdapter(private val activityContext: Activity) : RecyclerView.Adapter<ItemViewHolder>() {

    private var arrayList = ArrayList<Android>()

    fun setAdapterListData(arrayList: ArrayList<Android>) {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }

    fun getAdapterListData(): ArrayList<Android> {
        return arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_row, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(itemViewHolder: ItemViewHolder, position: Int) {
        val currentItem = arrayList[position]
        itemViewHolder.bind(activityContext, currentItem, position)
    }

    override fun getItemCount(): Int {
        return if (arrayList == null) 0 else arrayList.size
    }

    val isAdapterListEmpty: Boolean
        get() = itemCount == 0

    fun getItemAt(position: Int): Android? {
        return if (arrayList != null) arrayList[position] else null
    }

    fun removeAt(position: Int) {
        arrayList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(0, arrayList.size)
    }

    class ItemViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val logoImageView: ImageView = item.findViewById(R.id.logoImageView)
        private val codeNameTextView: TextView = item.findViewById(R.id.codeNameTextView)
        private val versionNumbersTextView: TextView = item.findViewById(R.id.versionNumbersTextView)
        private val apiLevelTextView: TextView = item.findViewById(R.id.apiLevelTextView)
        private val releaseDateTextView: TextView = item.findViewById(R.id.releaseDateTextView)

      /*private val logoImageView: ImageView
        private val codeNameTextView: TextView
        private val versionNumbersTextView: TextView
        private val apiLevelTextView: TextView
        private val releaseDateTextView: TextView

        init {
            logoImageView = item.findViewById(R.id.logoImageView)
            codeNameTextView = item.findViewById(R.id.codeNameTextView)
            versionNumbersTextView = item.findViewById(R.id.versionNumbersTextView)
            apiLevelTextView = item.findViewById(R.id.apiLevelTextView)
            releaseDateTextView = item.findViewById(R.id.releaseDateTextView)
        }*/

        fun bind(activityContext: Activity, android: Android, position: Int) {
            /*GlideCacheUtil.clearAllCache(activityContext)
            GlideImageLoader.load(
                 activityContext,
                 android.logo,
                 R.drawable.user_placeholder,
                 R.drawable.error_placeholder,
                 logoImageView,
                 object : GlideImageLoadingListener {
                     override fun imageLoadSuccess() {
                     }

                     override fun imageLoadError() {
                         Toast.makeText(activityContext, "An error occurred", Toast.LENGTH_SHORT).show()
                     }
                 })*/

            PicassoImageLoader.load(
                 activityContext,
                 android.logo,
                 R.drawable.user_placeholder,
                 R.drawable.error_placeholder,
                 logoImageView,
                 object : PicassoImageLoadingListener {
                     override fun imageLoadSuccess() {
                     }

                     override fun imageLoadError(exception: Exception?) {
                         Toast.makeText(activityContext, "An error occurred", Toast.LENGTH_SHORT).show()
                     }
                 })

            codeNameTextView.text = android.codeName
            versionNumbersTextView.text = android.versionNumbers
            apiLevelTextView.text = android.apiLevel
            releaseDateTextView.text = android.releaseDate
        }
    }
}