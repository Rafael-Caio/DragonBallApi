package com.rafaeltech.apidragonball.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Links(
    @SerializedName("first")
    val first: String,
    @SerializedName("previous")
    val previous: String? = null,
    @SerializedName("next")
    val next: String,
    @SerializedName("last")
    val last: String
): Parcelable

/*"first": "https://dragonball-api.com/api/characters?limit=10",
"previous": "",
"next": "https://dragonball-api.com/api/characters?page=2&limit=10",
"last": "https://dragonball-api.com/api/characters?page=6&limit=10"*/
