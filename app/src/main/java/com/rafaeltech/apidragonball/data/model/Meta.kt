package com.rafaeltech.apidragonball.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meta(
    @SerializedName("totalItems")
    val totalItems: Int,
    @SerializedName("itemCount")
    val itemCount: Int,
    @SerializedName("itemsPerPage")
    val itemsPerPage: Int,
    @SerializedName("totalPages")
    val totalPages: Int,
    @SerializedName("currentPage")
    val currentPage: Int
): Parcelable

/*"totalItems": 58,
"itemCount": 10,
"itemsPerPage": 10,
"totalPages": 6,
"currentPage": 1*/
