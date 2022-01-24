package ru.ds.movies_search.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.ds.movies_search.R
import ru.ds.movies_search.model.Film

class MainFragmentAdapter(private var onItemViewClickListener: OnItemViewClickListener?):  RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    private var moviesData: List<Film> = listOf()

    fun setWeather(data: List<Film>) {
        moviesData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int
    ): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recycler_films, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(moviesData[position])
    }

    override fun getItemCount() = moviesData.size


    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(film: Film) {
            itemView.apply {
                findViewById<TextView>(R.id.mainFragmentRecyclerItemTextView).text = film.movie.name

                setOnClickListener {
                    onItemViewClickListener?.onItemViewClick(film)
                }
            }
        }
    }

    fun removeListener() {
        onItemViewClickListener = null
    }
}