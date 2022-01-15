package com.example.pokemonapp.network.data.responses.GetPokemonListResponse

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Pokemon>
)