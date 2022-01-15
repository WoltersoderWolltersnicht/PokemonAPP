package com.example.pokemonapp.network.data.responses.GetPokemonResponse

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)