package com.example.mybar.API

import com.squareup.moshi.Json

data class IngredientItem(

	@Json(name="strIngredient1")
	val strIngredient1: String? = null
)