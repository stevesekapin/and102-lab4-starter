package com.codepath.campgrounds

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CampgroundAdapter(
    private val context: Context,
    private val campgrounds: MutableList<CampgroundEntity>
) : RecyclerView.Adapter<CampgroundAdapter.ViewHolder>() {

    companion object {
        const val EXTRA_CAMPGROUND = "extra_campground"
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvName)
        val descriptionTextView: TextView = itemView.findViewById(R.id.tvDescription)

        init {
            itemView.setOnClickListener {
                val campground = campgrounds[bindingAdapterPosition]

                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(EXTRA_CAMPGROUND, campground)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_campground, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val campground = campgrounds[position]
        holder.nameTextView.text = campground.name
        holder.descriptionTextView.text = campground.description
    }

    override fun getItemCount(): Int {
        return campgrounds.size
    }

    // 🔥 REQUIRED FOR OFFLINE MODE
    fun updateData(newCampgrounds: List<CampgroundEntity>) {
        campgrounds.clear()
        campgrounds.addAll(newCampgrounds)
        notifyDataSetChanged()
    }
}