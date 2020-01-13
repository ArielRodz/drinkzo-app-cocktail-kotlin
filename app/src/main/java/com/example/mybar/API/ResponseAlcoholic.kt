package com.example.mybar.API

import com.squareup.moshi.Json

data class ResponseAlcoholic(

	@Json(name="drinks")
	val drinks: List<AlcoholicItem?>? = null
)