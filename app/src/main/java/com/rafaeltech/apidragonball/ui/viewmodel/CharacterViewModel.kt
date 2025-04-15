package com.rafaeltech.apidragonball.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafaeltech.apidragonball.data.model.Character
import androidx.lifecycle.viewModelScope
import com.rafaeltech.apidragonball.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterViewModel(private val repository: CharacterRepository): ViewModel() {

    private val _results = MutableLiveData<List<Character>>()
    val results: LiveData<List<Character>> = _results

    private val currentList = mutableListOf<Character>()
    private var nextPageUrl: String? = null
    private var isLoading = false

    init {
        loadFirstPage()
    }

    //carrega a primeira página
    private fun loadFirstPage() {
        viewModelScope.launch {
            isLoading = true
            val response = repository.getCharacterResponse()
            response?.let {
                // Agora 'it' é do tipo 'CharacterResponse'
                currentList.addAll(it.results) // Isso deve funcionar se 'results' for uma lista de 'Character'
                _results.postValue(currentList)
                nextPageUrl = it.links.next
            }
            isLoading = false
        }
    }
    //carrega a próxima página
    fun loadNextPage() {
        if (isLoading || nextPageUrl == null) return

        viewModelScope.launch {
            isLoading = true
            val response = repository.getCharactersByUrl(nextPageUrl!!)
            response?.let {
                currentList.addAll(it.results)
                _results.postValue(currentList)
                nextPageUrl = it.links.next
            }
            isLoading = false
        }
    }
}
