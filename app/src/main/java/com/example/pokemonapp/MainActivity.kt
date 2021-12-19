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
import com.squareup.picasso.Picasso
import java.net.URL


class MainActivity : AppCompatActivity() {

    private val _pokemon : MutableLiveData<PokemonData> = MutableLiveData()
    val pokemon : LiveData<PokemonData> get() = _pokemon

    lateinit var textView : TextView
    lateinit var button : Button
    lateinit var  imageView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.nametxt)
        button = findViewById(R.id.button)
        imageView = findViewById(R.id.imageView)

        pokemon.observe(this,{
            textView.text = it.name

            Picasso.with(this).load(it.imamgeUrl)
                .into(imageView)
        })

        button.setOnClickListener{
            getPokemon(generateRandomNumber())
        }
    }

    fun generateRandomNumber():Int{
        return Random.nextInt(800 - 1) + 1
    }

    fun getPokemon(id:Int) {
        val queue = Volley.newRequestQueue(this)
        val url = "https://pokeapi.co/api/v2/pokemon/"+id

        var pokemon : PokemonData? = null

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                val jsonResponse = JSONObject(response)

                val id = jsonResponse.getInt("id")
                val name = jsonResponse.getString("name")
                val image = jsonResponse.getJSONObject("sprites").getString("front_default")
                pokemon = PokemonData(id, name, image)
                _pokemon.value=pokemon
            },
            Response.ErrorListener {
                Log.e("error", "no Pokemon")
            },
        )
        queue.add(stringRequest)
    }
}