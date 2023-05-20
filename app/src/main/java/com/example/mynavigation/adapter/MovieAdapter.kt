package com.example.mynavigation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieexample.url
import com.example.mynavigation.entity.Movie
import com.example.mynavigation.R


class MovieAdapter(val context: Context, val data:List<Movie>):RecyclerView.Adapter<MovieAdapter.MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_layout, parent, false))

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        bind(holder,data[position])
    }




    private fun bind(holder: MyViewHolder, movie: Movie) {

        holder.apply {
            name.text  = movie.etat
            year.text =  movie.type.toString()
        }

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById (R.id.textName) as TextView
        val year = view.findViewById (R.id.textYear) as TextView
        val movieImage = view.findViewById (R.id.imageMovie) as ImageView


    }

}