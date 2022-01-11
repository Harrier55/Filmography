package ru.harrier55.project.filmography.ui.filmlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.harrier55.project.filmography.R


class FilmListNavigationButtonAdapter():RecyclerView.Adapter<FilmListNavigationButtonAdapter.FilmNavigationViewHolder>() {

    private val  list = listOf("Фильмы", "Сериалы", "Мульт","Anime", "Animal", "Show","Mini", "Video")

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmNavigationViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_navigation,parent,false)
        return FilmNavigationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FilmNavigationViewHolder, position: Int) {
        holder.navigationText.text = list[position]
    }



    class FilmNavigationViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val navigationText:TextView = itemView.findViewById(R.id.film_navigation_text_view)

    }


}