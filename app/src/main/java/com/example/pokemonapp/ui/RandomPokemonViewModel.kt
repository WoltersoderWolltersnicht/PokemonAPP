package com.example.pokemonapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokemonapp.network.api.IGetPokemonService
import com.example.pokemonapp.network.api.Retrofit.RetroInstance
import com.example.pokemonapp.network.data.responses.GetPokemonResponse.GetPokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RandomPokemonViewModel {

    private val _pokemon : MutableLiveData<GetPokemonResponse> = MutableLiveData()
    val pokemon : LiveData<GetPokemonResponse> get() = _pokemon

    fun getRandomPokemon(id:Int) {
        val retroInstance = RetroInstance.getRetroInstance()
        val retroService = retroInstance.create(IGetPokemonService:: class.java)
        val call = retroService.getPokemons(id)

        call.enqueue(object: Callback<GetPokemonResponse>{
            override fun onFailure(call: Call<GetPokemonResponse>, t: Throwable ){
                _pokemon.postValue(null)
            }

            override fun onResponse(
                call: Call<GetPokemonResponse>,
                response: Response<GetPokemonResponse>
            ){
                _pokemon.postValue(response.body())
            }
        })
    }

}