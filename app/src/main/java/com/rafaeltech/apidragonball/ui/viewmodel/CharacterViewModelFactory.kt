package com.rafaeltech.apidragonball.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rafaeltech.apidragonball.repository.CharacterRepository

@Suppress("UNCHECKED_CAST")
class CharacterViewModelFactory (private val repository: CharacterRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if ( modelClass.isAssignableFrom(CharacterViewModel::class.java)){
            return CharacterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class") // Classe ViewModel desconhecida
    }
}