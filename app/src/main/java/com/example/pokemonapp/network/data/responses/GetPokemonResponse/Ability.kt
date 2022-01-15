package com.example.pokemonapp.network.data.responses.GetPokemonResponse

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)