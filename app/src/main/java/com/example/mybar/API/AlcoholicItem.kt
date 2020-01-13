package com.example.mybar.API

import com.squareup.moshi.Json

data class AlcoholicItem(

	@Json(name="strAlcoholic")
	val strAlcoholic: String? = null
)