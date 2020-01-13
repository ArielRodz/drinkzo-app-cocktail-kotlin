package com.example.mybar.Utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mybar.API.DrinksItem
import com.example.mybar.Detail.IngredientItem

fun Activity.toastShort(mensaje: String){
    Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
}


fun Activity.toastLong(mensaje: String){
    Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
}

fun ViewGroup.inflate(layoutId:Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}


fun tranformToList(drinkItem: DrinksItem) :List<IngredientItem>{

    val listIngredients = listOf(
    IngredientItem(drinkItem?.strIngredient1,drinkItem?.strMeasure1),
    IngredientItem(drinkItem?.strIngredient2,drinkItem?.strMeasure2),
    IngredientItem(drinkItem?.strIngredient3,drinkItem?.strMeasure3),
    IngredientItem(drinkItem?.strIngredient4,drinkItem?.strMeasure4),
    IngredientItem(drinkItem?.strIngredient5,drinkItem?.strMeasure5),
    IngredientItem(drinkItem?.strIngredient6,drinkItem?.strMeasure6),
    IngredientItem(drinkItem?.strIngredient7,drinkItem?.strMeasure7),
    IngredientItem(drinkItem?.strIngredient8,drinkItem?.strMeasure8),
    IngredientItem(drinkItem?.strIngredient9,drinkItem?.strMeasure9),
    IngredientItem(drinkItem?.strIngredient10,drinkItem?.strMeasure10),
    IngredientItem(drinkItem?.strIngredient11,drinkItem?.strMeasure11),
    IngredientItem(drinkItem?.strIngredient12,drinkItem?.strMeasure12),
    IngredientItem(drinkItem?.strIngredient13,drinkItem?.strMeasure13),
    IngredientItem(drinkItem?.strIngredient14,drinkItem?.strMeasure14),
    IngredientItem(drinkItem?.strIngredient15,drinkItem?.strMeasure15))
    return listIngredients
}