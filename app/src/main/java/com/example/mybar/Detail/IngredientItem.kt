package com.example.mybar.Detail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IngredientItem (
    val ingredient: String? = null,
    val measure: String? = null
): Parcelable