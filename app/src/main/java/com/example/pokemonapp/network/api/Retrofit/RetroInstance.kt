package com.example.pokemonapp.network.api.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object{
        val BASE_URL = "https://pokeapi.co/api/v2/"

        fun getRetroInstance(): Retrofit {
            return  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}