package com.example.sprint_3_conectividad_y_buenas_pr_cticas_de_seguridad

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sprint_3_conectividad_y_buenas_pr_cticas_de_seguridad.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RickMortyAdapter
    private val characterNames = mutableListOf<Character>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svSearchCharacterByName.clearFocus()
        binding.svSearchCharacterByName.setOnSearchClickListener(this)
        initRecyclerView()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        searchByCharacterName("rick")
    }

    private fun initRecyclerView() {
        adapter = RickMortyAdapter(characterNames)
        binding.rvListNameCharacters.layoutManager = LinearLayoutManager(this)
        binding.rvListNameCharacters.adapter = adapter
        adapter.onItemClick = {
            val intent = Intent(this, MainActivity2::class.java)

            intent.putExtra("NAME", it.name)
            intent.putExtra("STATUS", it.status)
            intent.putExtra("SPECIES", it.species)
            intent.putExtra("SUBSPECIES", it.subspecies)
            intent.putExtra("GENDER", it.gender)
            intent.putExtra("ORIGIN", it.origin?.place)
            intent.putExtra("LOCATION", it.location?.place)
            intent.putExtra("IMAGE", it.image)

            startActivity(intent)
        }

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByCharacterName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(RickMortyApiService::class.java)
                .getCharacterByName("character/?name=$query")
            val characterBody = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    binding.progressBarItem.visibility = View.GONE
                    val characters = characterBody?.characters ?: emptyList()
                    characterNames.clear()
                    characterNames.addAll(characters)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Algo fué mal", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(p0: View?) {
        searchByCharacterName(binding.svSearchCharacterByName.query.toString().lowercase())
    }

}