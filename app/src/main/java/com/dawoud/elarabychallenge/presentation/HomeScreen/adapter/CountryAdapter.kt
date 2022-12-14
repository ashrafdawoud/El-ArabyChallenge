package com.dawoud.elarabychallenge.presentation.HomeScreen.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dawoud.elarabychallenge.R
import com.dawoud.elarabychallenge.domain.model.homeScreen.NewsModel
import com.dawoud.elarabychallenge.presentation.HomeScreen.HomeScreenFragment
import com.dawoud.elarabychallenge.presentation.HomeScreen.HomeScreenFragmentDirections
import com.squareup.picasso.Picasso

class CountryAdapter(val  context: Context, val data:List<NewsModel>) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView =itemView.findViewById(R.id.title)
        val time: TextView =itemView.findViewById(R.id.time)
        val image: ImageView =itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false))
    }

    override fun onBindViewHolder(holder: CountryAdapter.ViewHolder, position: Int) {
        holder.title.setText(data.get(position).title)
        holder.time.setText(data.get(position).publishedAt)
        Picasso.get().load(data.get(position).urlToImage).into(holder.image)
        holder.itemView.setOnClickListener {
            val action = HomeScreenFragmentDirections.actionHomeScreenFragmentToDetailsFragment(data.get(position))
            it.findNavController().navigate(action)

        }
    }
    override fun getItemCount(): Int {
        return data.size
    }
}