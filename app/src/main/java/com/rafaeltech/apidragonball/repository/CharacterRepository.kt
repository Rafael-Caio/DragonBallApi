package com.rafaeltech.apidragonball.repository

import com.rafaeltech.apidragonball.data.model.CharacterResponse
import com.rafaeltech.apidragonball.data.remote.CharactersApiService

class CharacterRepository(
    private val apiService: CharactersApiService
) {
    suspend fun getCharacterResponse(): CharacterResponse? {
        val response = apiService.getCharacter()
        return if (response.isSuccessful) {
            response.body() //tipo CharacterResponse
        } else {
            null
        }
    }
    suspend fun getCharactersByUrl(url: String): CharacterResponse? {
        val response = apiService.getCharacterByUrl(url)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

}