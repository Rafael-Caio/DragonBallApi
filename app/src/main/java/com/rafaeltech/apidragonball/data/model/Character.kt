package com.rafaeltech.apidragonball.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Character(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("ki")
    val ki: String? = null,
    @SerializedName("maxki")
    val maxki: String? = null,
    @SerializedName("race")
    val race: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("affiliation")
    val affiliation: String? = null,
    @SerializedName("deletedAt")
    val deletedAt: String? = null
): Serializable
