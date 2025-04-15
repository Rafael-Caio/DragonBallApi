package com.rafaeltech.apidragonball.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rafaeltech.apidragonball.data.model.Character
import com.rafaeltech.apidragonball.databinding.ItemCharacterBinding

class CharacterAdapter(
    val context: Context
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(){

    private var listCharacters = emptyList<Character>()
    private var action: ((Character) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun getItemCount() = listCharacters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bindViews(listCharacters[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCharacters(newList: List<Character>) {
        listCharacters = newList
        notifyDataSetChanged()//avisa para o recuclerView a modificação na lista
    }

    fun setAction(action: (Character) -> Unit) {
        this.action = action
    }

    inner class CharacterViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindViews(character: Character) {
            binding.apply {
                tvLabelName.text = character.name
                tvValueRaceGender.text = "${character.race} - ${character.gender}"
                tvValueKi.text = character.ki.toString()
                tvValueKi.text = character.ki.toString()
                tvValueTotalKi.text = character.maxki.toString()
                tvValueAffiliation.text = character.affiliation
            }

            Glide.with(context)
                .load(character.image)
                .into(binding.ivCharacter)

            binding.cvCharacter.setOnClickListener {
                action?.invoke(character)
            }

        }
    }
}