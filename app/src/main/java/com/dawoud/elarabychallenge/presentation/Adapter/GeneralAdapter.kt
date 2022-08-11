package com.dawoud.elarabychallenge.presentation.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dawoud.elarabychallenge.R
import com.dawoud.elarabychallenge.domain.model.NewsModel
import com.squareup.picasso.Picasso

class GeneralAdapter(val  context: Context, val data:List<NewsModel>) : RecyclerView.Adapter<GeneralAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView =itemView.findViewById(R.id.title)
        val time: TextView =itemView.findViewById(R.id.time)
        val image: ImageView =itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.popular_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.setText(data.get(position).title)
        holder.time.setText(data.get(position).publishedAt)
        Picasso.get().load(data.get(position).urlToImage).into(holder.image)
    }
    override fun getItemCount(): Int {
        return data.size
    }
}