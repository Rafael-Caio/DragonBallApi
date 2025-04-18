package com.rafaeltech.apidragonball.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.rafaeltech.apidragonball.R
import com.rafaeltech.apidragonball.databinding.FragmentSecondBinding
import kotlin.getValue

class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private var buttonActivate = false

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: SecondFragmentArgs by navArgs()

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }

        val btnActivate = view?.findViewById<ImageButton>(R.drawable.activate)
        val btnNoActivate = view?.findViewById<ImageButton>(R.drawable.noactivate)
        // Ação de clique no botão de favorito
        binding.favoriteButton.setOnClickListener {
            if (buttonActivate) {
                binding.favoriteButton.setImageResource(R.drawable.noactivate)
            } else {
                binding.favoriteButton.setImageResource(R.drawable.activate)
            }
            buttonActivate = !buttonActivate
        }

        return binding.root

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val character = args.currentCharacter
        Glide.with(requireContext())
            .load(character.image)
            .into(binding.imgSecondFrag)

        binding.txtSecond.text = character.description ?: "Sem descrição disponível."
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}