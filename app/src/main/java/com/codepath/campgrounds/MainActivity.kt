package com.codepath.campgrounds

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    companion object {
        const val CAMPGROUND_EXTRA = "CAMPGROUND_EXTRA"
    }

    private val campgrounds = mutableListOf<Campground>()
    private lateinit var campgroundAdapter: CampgroundAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        campgroundAdapter = CampgroundAdapter(this, campgrounds)
        recyclerView.adapter = campgroundAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchCampgrounds()
    }

    private fun fetchCampgrounds() {
        val client = AsyncHttpClient()
        val url =
            "https://developer.nps.gov/api/v1/campgrounds?api_key=1Gt76L1MGC1Ygg6rtf8gD9E9Lb5ghcjnXKfbx9qt"

        client.get(url, object : JsonHttpResponseHandler() {

            override fun onSuccess(
                statusCode: Int,
                headers: Headers?,
                json: JSON?
            ) {
                val dataArray = json!!.jsonObject.getJSONArray("data")

                for (i in 0 until dataArray.length()) {
                    val obj = dataArray.getJSONObject(i)

                    val name = obj.getString("name")
                    val description = obj.getString("description")
                    val latLong = obj.getString("latLong")

                    val imagesArray = obj.getJSONArray("images")
                    val imageList = mutableListOf<Image>()

                    for (j in 0 until imagesArray.length()) {
                        val imageObj = imagesArray.getJSONObject(j)
                        imageList.add(Image(imageObj.getString("url")))
                    }

                    val campground =
                        Campground(name, description, latLong, imageList)

                    campgrounds.add(campground)
                }

                campgroundAdapter.notifyDataSetChanged()
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                throwable?.printStackTrace()
            }
        })
    }
}