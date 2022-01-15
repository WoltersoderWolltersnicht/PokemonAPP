package com.example.pokemonapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemonapp.Utils
import com.example.pokemonapp.databinding.FragmentRandomPokemonBinding
import com.example.pokemonapp.models.PokemonData
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
            activity?.let { it1 ->
                viewModel.getRandomPokemon(Utils.generateRandomNumber(),
                    it1.applicationContext)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun loadPokemon(pokemonData: PokemonData){
        binding.nametxt.text = pokemonData.name;

        Picasso.with(activity).load(pokemonData.imamgeUrl)
            .into(binding.imageView)
    }
}