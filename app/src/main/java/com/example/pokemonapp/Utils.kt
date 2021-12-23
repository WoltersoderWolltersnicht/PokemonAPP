package com.example.pokemonapp

import kotlin.random.Random

class Utils {
    companion object {
        @JvmStatic
        fun generateRandomNumber():Int{
            return Random.nextInt(800 - 1) + 1
        }
    }
}