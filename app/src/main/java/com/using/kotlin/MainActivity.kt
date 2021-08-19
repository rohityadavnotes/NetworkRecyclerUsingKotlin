package com.using.kotlin

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.using.kotlin.adapter.RecyclerViewAdapter
import com.using.kotlin.adapter.RecyclerViewItemClickListener
import com.using.kotlin.adapter.RecyclerViewTouchListener
import com.using.kotlin.base.BaseActivity
import com.using.kotlin.data.remote.ApiService
import com.using.kotlin.data.remote.ApiServiceGenerator.createService
import com.using.kotlin.model.Android
import com.using.kotlin.model.Data
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class MainActivity : BaseActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var androidArrayList: ArrayList<Android>
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun initializeView() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    override fun initializeObject() {
        androidArrayList = ArrayList()

        recyclerView.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager

        recyclerView.itemAnimator = DefaultItemAnimator()
        val itemDecoration: ItemDecoration = DividerItemDecoration(
            this,
            DividerItemDecoration.VERTICAL
        )
        recyclerView.addItemDecoration(itemDecoration)

        recyclerViewAdapter = RecyclerViewAdapter(this)
        recyclerView.adapter = recyclerViewAdapter

        getVersions();
    }

    override fun initializeToolBar() {
    }

    override fun initializeCallbackListener() {
    }

    override fun addTextChangedListener() {
    }

    override fun setOnClickListener() {
        recyclerView.addOnItemTouchListener(RecyclerViewTouchListener(applicationContext, recyclerView, object : RecyclerViewItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        Toast.makeText(applicationContext, "position : " + androidArrayList[position].codeName, Toast.LENGTH_SHORT).show()
                        val android = androidArrayList.get(position)
                        val intent = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra("parcelable_android_key", android)
                        startActivity(intent)
                    }

                    override fun onItemLongClick(view: View?, position: Int) {
                        Toast.makeText(applicationContext, "position : " + androidArrayList[position].codeName, Toast.LENGTH_SHORT).show()
                        recyclerViewAdapter.removeAt(position)
                    }
                })
        )
    }

    fun getVersions() {
        val progressDialog: ProgressDialog
        progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait...")
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.setCancelable(false)

        val apiService = createService(this@MainActivity, ApiService::class.java)

        val observable: Observable<Response<Data>> = apiService.getAndroidVersion()
        val observer: Observer<Response<Data>> = object : Observer<Response<Data>> {
            override fun onSubscribe(disposable: Disposable) {
                progressDialog.show();
            }

            override fun onNext(response: Response<Data>) {
                progressDialog.dismiss();
                if (response != null) {
                    if (response.body() != null && response.isSuccessful()) {
                        androidArrayList = response.body()?.data!!
                        Toast.makeText(applicationContext, "" + androidArrayList.size, Toast.LENGTH_SHORT).show()
                        recyclerViewAdapter.setAdapterListData(androidArrayList)
                    }
                }
            }

            override fun onError(e: Throwable) {
                progressDialog.dismiss();
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }

            override fun onComplete() {}
        }
        observable
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}