package com.example.sprint_3_conectividad_y_buenas_pr_cticas_de_seguridad

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.sprint_3_conectividad_y_buenas_pr_cticas_de_seguridad.databinding.ItemRvCharacterNameBinding

class RickMortyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemRvCharacterNameBinding.bind(view)

    fun bind(character: Character) {
        binding.tvCharacterName.text = character.name
        //binding.root.setOnClickListener(goToDetails())
    }
}