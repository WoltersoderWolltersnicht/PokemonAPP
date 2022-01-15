package com.example.pokemonapp.network.api

import com.example.pokemonapp.network.data.responses.GetPokemonResponse.GetPokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IGetPokemonService {
    @GET("pokemon/{id}")
    fun getPokemons(@Path("id") pokemonId:Int): Call<GetPokemonResponse>
}