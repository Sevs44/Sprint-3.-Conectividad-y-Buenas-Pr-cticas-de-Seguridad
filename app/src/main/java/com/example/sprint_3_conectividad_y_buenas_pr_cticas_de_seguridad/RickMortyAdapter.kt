package com.example.sprint_3_conectividad_y_buenas_pr_cticas_de_seguridad

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RickMortyAdapter(private val characters: List<Character>) :
    RecyclerView.Adapter<RickMortyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickMortyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RickMortyViewHolder(
            layoutInflater.inflate(
                R.layout.item_rv_character_name,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: RickMortyViewHolder, position: Int) {
        val item = characters[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    var onItemClick: ((Character) -> Unit)? = null

}