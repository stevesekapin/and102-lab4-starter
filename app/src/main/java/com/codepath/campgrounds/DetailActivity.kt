package com.codepath.campgrounds

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val campground =
            intent.getSerializableExtra(MainActivity.CAMPGROUND_EXTRA) as? Campground
                ?: return

        val imageView = findViewById<ImageView>(R.id.detailImage)
        val nameTextView = findViewById<TextView>(R.id.detailName)
        val locationTextView = findViewById<TextView>(R.id.detailLocation)
        val descriptionTextView = findViewById<TextView>(R.id.detailDescription)

        nameTextView.text = campground.name
        locationTextView.text = campground.latLong
        descriptionTextView.text = campground.description

        Glide.with(this)
            .load(campground.getImageUrl())
            .into(imageView)
    }
}