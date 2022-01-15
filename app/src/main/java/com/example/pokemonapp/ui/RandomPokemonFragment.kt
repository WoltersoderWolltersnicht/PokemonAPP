package com.example.pokemonapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemonapp.Utils
import com.example.pokemonapp.databinding.FragmentRandomPokemonBinding
import com.example.pokemonapp.models.PokemonData
import com.example.pokemonapp.network.data.responses.GetPokemonResponse.GetPokemonResponse
import com.squareup.picasso.Picasso

class RandomPokemonFragment : Fragment() {

    private var _binding: FragmentRandomPokemonBinding? = null
    private val binding get() = _binding!!

    private var viewModel: RandomPokemonViewModel = RandomPokemonViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRandomPokemonBinding.inflate(inflater, container, false)

        viewModel.pokemon.observe(viewLifecycleOwner,{
            loadPokemon(it)
        })

        binding.button.setOnClickListener{
                viewModel.getRandomPokemon(Utils.generateRandomNumber())
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun loadPokemon(pokemonData: GetPokemonResponse?){
        if(pokemonData != null) {
            binding.nametxt.text = pokemonData.name;

            Picasso.with(activity).load(pokemonData.sprites.front_default)
                .into(binding.imageView)
        }
    }
}