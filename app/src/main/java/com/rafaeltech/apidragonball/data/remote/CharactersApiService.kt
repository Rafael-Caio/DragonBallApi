package com.rafaeltech.apidragonball.data.remote

import com.rafaeltech.apidragonball.data.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CharactersApiService {
    @GET("characters")
    suspend fun getCharacter(): Response<CharacterResponse>

    // Metodo para pegar personagens por URL
    @GET
    suspend fun getCharacterByUrl(@Url url: String): Response<CharacterResponse>
}