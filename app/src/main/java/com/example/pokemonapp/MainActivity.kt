package com.example.pokemonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemonapp.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding
    private var viewModel: MainViewModel = MainViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.pokemon.observe(this,{
            loadPokemon(it)
        })

        binding.button.setOnClickListener{
            viewModel.getRandomPokemon(Utils.generateRandomNumber(),this)
        }
    }

    fun loadPokemon(pokemonData: PokemonData){
        binding.nametxt.text = pokemonData.name;

        Picasso.with(this).load(pokemonData.imamgeUrl)
            .into(binding.imageView)
    }
}