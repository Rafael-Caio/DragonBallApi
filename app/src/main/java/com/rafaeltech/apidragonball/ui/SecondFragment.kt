package com.rafaeltech.apidragonball.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.rafaeltech.apidragonball.databinding.FragmentSecondBinding
import kotlin.getValue

class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
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