package com.codepath.campgrounds

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CampgroundAdapter
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CampgroundAdapter(this, mutableListOf())
        recyclerView.adapter = adapter

        database = AppDatabase.getInstance(this)

        fetchCampgrounds()
    }

    private fun fetchCampgrounds() {

        val client = AsyncHttpClient()
        val url =
            "https://developer.nps.gov/api/v1/campgrounds?parkCode=yose&api_key=1Gt76L1MGC1Ygg6rtf8gD9E9Lb5ghcjnXKfbx9qt"

        client.get(url, object : JsonHttpResponseHandler() {

            override fun onSuccess(
                statusCode: Int,
                headers: Headers?,
                json: JSON
            ) {

                val dataArray = json.jsonObject.getJSONArray("data")
                val campgrounds = mutableListOf<CampgroundEntity>()

                for (i in 0 until dataArray.length()) {

                    val campgroundJson = dataArray.getJSONObject(i)

                    val name = campgroundJson.getString("name")
                    val description = campgroundJson.getString("description")
                    val latLong = campgroundJson.optString("latLong", "Not Available")

                    val imagesArray = campgroundJson.getJSONArray("images")
                    val imageUrl =
                        if (imagesArray.length() > 0)
                            imagesArray.getJSONObject(0).getString("url")
                        else
                            ""

                    val entity = CampgroundEntity(
                        name = name,
                        description = description,
                        latLong = latLong,
                        imageUrl = imageUrl
                    )

                    campgrounds.add(entity)
                }

                lifecycleScope.launch(Dispatchers.IO) {
                    database.campgroundDao().deleteAll()
                    database.campgroundDao().insertAll(campgrounds)

                    val saved = database.campgroundDao().getAll()

                    launch(Dispatchers.Main) {
                        adapter.updateData(saved)
                    }
                }
            }

            // 🔥 REQUIRED — YOU WERE MISSING THIS
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                lifecycleScope.launch(Dispatchers.IO) {

                    val saved = database.campgroundDao().getAll()

                    launch(Dispatchers.Main) {
                        adapter.updateData(saved)
                    }
                }
            }
        })
    }
}