package com.codepath.campgrounds

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val campground =
            intent.getSerializableExtra(CampgroundAdapter.EXTRA_CAMPGROUND)
                    as? CampgroundEntity ?: return

        val nameTextView = findViewById<TextView?>(R.id.tvName)
        val descriptionTextView = findViewById<TextView?>(R.id.tvDescription)
        val locationTextView = findViewById<TextView?>(R.id.tvLocation)

        nameTextView?.text = campground.name ?: ""
        descriptionTextView?.text = campground.description ?: ""
        locationTextView?.text = campground.latLong ?: ""
    }
}