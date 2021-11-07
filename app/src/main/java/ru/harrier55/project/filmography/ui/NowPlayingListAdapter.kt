package ru.harrier55.project.filmography.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.data.CardFilm


class NowPlayingListAdapter(private val cardFilms: List<CardFilm>) :
    RecyclerView.Adapter<NowPlayingListAdapter.NowPlayingViewHolder>() {


    override fun getItemCount(): Int = cardFilms.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card_film, parent, false)
        return NowPlayingViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        onBind(holder, position)
    }

    class NowPlayingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val filmPoster: ImageView = itemView.findViewById(R.id.film_poster_image_view)
        val filmName: TextView = itemView.findViewById(R.id.film_name_text_view)
        val filmYearPremiere: TextView = itemView.findViewById(R.id.film_year_premier_text_view)
        val filmRating: TextView = itemView.findViewById(R.id.film_rating_text_view)
    }

    private fun onBind(holder: NowPlayingViewHolder, position: Int) {
        holder.filmPoster.setImageResource(cardFilms[position].filmPoster)
        holder.filmName.text = cardFilms[position].filmName
        holder.filmYearPremiere.text = cardFilms[position].filmYear_premiere
        holder.filmRating.text = cardFilms[position].filmRating
    }
}




