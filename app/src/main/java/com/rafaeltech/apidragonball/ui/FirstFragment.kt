package com.rafaeltech.apidragonball.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafaeltech.apidragonball.data.remote.CharactersApiService
import com.rafaeltech.apidragonball.data.remote.NetworkConfig
import com.rafaeltech.apidragonball.databinding.FragmentFirstBinding
import com.rafaeltech.apidragonball.repository.CharacterRepository
import com.rafaeltech.apidragonball.ui.viewmodel.CharacterViewModel
import com.rafaeltech.apidragonball.ui.viewmodel.CharacterViewModelFactory
import kotlin.getValue

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
// private const val ARG_PARAM1 = "param1"
// private const val ARG_PARAM2 = "param2"

class FirstFragment : Fragment() {


    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterMain: CharacterAdapter

    private val characterViewModel: CharacterViewModel by viewModels {
        CharacterViewModelFactory(
            CharacterRepository(
                NetworkConfig.getInstance().create(CharactersApiService::class.java)
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observers()
        setListener()
    }

    private fun setupRecyclerView() {
        binding.rvCharacter.apply {
            adapterMain = CharacterAdapter(requireContext())
            adapter = adapterMain
            layoutManager = GridLayoutManager(requireContext(), 2)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val layoutManager = recyclerView.layoutManager as GridLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                    // Quando estiver faltando 4 ou menos itens para o final...
                    if (totalItemCount <= (lastVisibleItemPosition + 4)) {
                        characterViewModel.loadNextPage()
                    }
                }
            })
        }
    }


    private fun setListener() {
        adapterMain.setAction { character ->
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(character)
            NavHostFragment.findNavController(this).navigate(action)
        }

    }

    private fun observers() {
        characterViewModel.results.observe(viewLifecycleOwner) { list ->
            adapterMain.updateCharacters(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

