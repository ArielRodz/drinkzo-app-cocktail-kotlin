package com.example.mybar.API

import com.example.mybar.API.IngredientItem
import com.squareup.moshi.Json

data class ResponseIngredient(

	@Json(name="drinks")
	val drinks: List<IngredientItem?>? = null
)