package com.rafaeltech.apidragonball.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterResponse(
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("items")
    val results: List<Character>,
    @SerializedName("links")
    val links: Links
): Parcelable
