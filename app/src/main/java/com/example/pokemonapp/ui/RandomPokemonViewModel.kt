package com.example.pokemonapp.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pokemonapp.models.PokemonData
import org.json.JSONObject

class RandomPokemonViewModel {

    private val _pokemon : MutableLiveData<PokemonData> = MutableLiveData()
    val pokemon : LiveData<PokemonData> get() = _pokemon

    fun getRandomPokemon(id:Int, context: Context) {
        val queue = Volley.newRequestQueue(context)
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