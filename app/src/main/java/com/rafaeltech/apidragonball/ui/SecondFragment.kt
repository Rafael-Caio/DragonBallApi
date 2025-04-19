package com.rafaeltech.apidragonball.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
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

        //val btnActivate = view?.findViewById<ImageButton>(R.drawable.activate)
        //val btnNoActivate = view?.findViewById<ImageButton>(R.drawable.noactivate)
        // Ação de clique no botão de favorito
        binding.favoriteButton.setOnClickListener {
            if (buttonActivate) {
                binding.favoriteButton.setImageResource(R.drawable.noactivate)
                deleteFavoriteCharacter(
                    requireView(),
                    args.currentCharacter.name
                )
            } else {
                binding.favoriteButton.setImageResource(R.drawable.activate)
                addFavoriteCharacters(
                    requireView(),
                    args.currentCharacter.name,
                    args.currentCharacter.id
                )
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

        // Verifica se já é favorito
        verificarFavorito(character.name)
    }

    //Mensagem do db collection
    private fun mensagem(view: View, texto: String, corHex: String) {
        val snackbar = com.google.android.material.snackbar.Snackbar.make(view, texto, com.google.android.material.snackbar.Snackbar.LENGTH_SHORT)
        snackbar.view.setBackgroundColor(android.graphics.Color.parseColor(corHex))
        val snackbarText =
            snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        snackbarText.textSize = 20f

        //Adicionando icon a esquerda do texto no snackbar
        val icon = ContextCompat.getDrawable(view.context, R.drawable.esferadragon)
        icon?.setBounds(0, 0, icon.intrinsicWidth, icon.intrinsicHeight)
        snackbarText.setCompoundDrawables(icon, null, null, null)
        snackbarText.compoundDrawablePadding = 16
        snackbar.show()
    }

    //adiciona o character ao banco de favorite
    private fun addFavoriteCharacters(view: View, name: String, id: Int){
        val db = FirebaseFirestore.getInstance()
        val dadosCharacter = hashMapOf(
            "name" to name,
            "id" to id
        )
        db.collection("FavoriteCharacters").document(name).set(dadosCharacter).addOnCompleteListener {
            mensagem(view,"Personagem adicionado aos favoritos", "#FF03DAC5")
        }.addOnFailureListener {
            mensagem(view,"Error ao adicionar!", "#FF0000")
        }
    }

    //Deleta o character do banco favoritos
    private fun deleteFavoriteCharacter(view: View, name: String) {
        val db = FirebaseFirestore.getInstance()
        db.collection("FavoriteCharacters").document(name).delete()
            .addOnSuccessListener {
                mensagem(view, "Personagem removido dos favoritos!", "#FF9800")
            }
            .addOnFailureListener {
                mensagem(view, "Erro ao remover!", "#FF0000")
            }
    }

    //Verifica se o Character esta nos favoritos
    private fun verificarFavorito(nome: String) {
        val db = FirebaseFirestore.getInstance()
        db.collection("FavoriteCharacters").document(nome).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    binding.favoriteButton.setImageResource(R.drawable.activate)
                    buttonActivate = true
                } else {
                    binding.favoriteButton.setImageResource(R.drawable.noactivate)
                    buttonActivate = false
                }
            }
            .addOnFailureListener {
                // Em caso de erro, mantém o botão como não ativado
                binding.favoriteButton.setImageResource(R.drawable.noactivate)
                buttonActivate = false
            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}