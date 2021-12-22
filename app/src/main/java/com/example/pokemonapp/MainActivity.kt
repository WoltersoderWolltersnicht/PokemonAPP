package com.example.pokemonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import kotlin.random.Random
import android.graphics.BitmapFactory

import android.graphics.Bitmap
import android.widget.ImageView
import com.example.pokemonapp.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import java.net.URL


class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding
    private var viewModel: MainViewModel = MainViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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