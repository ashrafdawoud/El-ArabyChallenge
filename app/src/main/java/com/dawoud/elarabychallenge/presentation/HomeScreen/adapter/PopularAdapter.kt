package com.dawoud.elarabychallenge.presentation.HomeScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dawoud.elarabychallenge.R
import com.dawoud.elarabychallenge.domain.model.homeScreen.NewsModel
import com.dawoud.elarabychallenge.presentation.HomeScreen.HomeScreenFragmentDirections
import com.squareup.picasso.Picasso

class PopularAdapter(val  context: Context, val data:List<NewsModel>) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView =itemView.findViewById(R.id.title)
        val time: TextView =itemView.findViewById(R.id.time)
        val image: ImageView =itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularAdapter.ViewHolder {
        return PopularAdapter.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.popular_item, parent, false))
    }

    override fun onBindViewHolder(holder: PopularAdapter.ViewHolder, position: Int) {
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