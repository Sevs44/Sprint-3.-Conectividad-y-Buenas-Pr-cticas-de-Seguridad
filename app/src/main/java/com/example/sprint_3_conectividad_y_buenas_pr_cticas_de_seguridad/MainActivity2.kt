package com.example.sprint_3_conectividad_y_buenas_pr_cticas_de_seguridad

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sprint_3_conectividad_y_buenas_pr_cticas_de_seguridad.databinding.CharacterDetailsBinding
import com.squareup.picasso.Picasso

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: CharacterDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = CharacterDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ibBackButton.setOnClickListener {
            goBack()
        }

        val character = getDetails(intent)
        setDetails(character)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.characterDetails)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun goBack() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun getDetails(intent: Intent): Character {
        val name = intent.getStringExtra("NAME")
        val status = intent.getStringExtra("STATUS")
        val species = intent.getStringExtra("SPECIES")
        val subspecies = intent.getStringExtra("SUBSPECIES")
        val gender = intent.getStringExtra("GENDER")
        val origin = intent.getStringExtra("ORIGIN")
        val location = intent.getStringExtra("LOCATION")
        val image = intent.getStringExtra("IMAGE")

        val character = Character(
            name = name,
            status = status,
            species = species,
            subspecies = subspecies,
            gender = gender,
            origin = Origin(origin, null),
            location = Location(location, null),
            image = image,

            url = null,
            dateCreated = null,
            id = null,
            episode = null
        )
        return character
    }


    private fun setDetails(character: Character?) {
        Picasso.get().load(character?.image).into(binding.ivCharacterImage)
        binding.tvCharacterName.text = character?.name
        binding.tvCharacterStatus.text = character?.status
        binding.tvCharacterSpecies.text = character?.species
        binding.tvCharacterType.text = character?.subspecies
        binding.tvCharacterGender.text = character?.gender
        binding.tvCharacterOrigin.text = character?.origin?.place
        binding.tvCharacterLocation.text = character?.location?.place
    }

}