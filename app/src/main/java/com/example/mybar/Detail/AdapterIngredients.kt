package com.example.mybar.Detail

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mybar.API.DrinksItem
import com.example.mybar.Detail.DetailActivity
import com.example.mybar.R
import com.example.mybar.Utils.inflate
import kotlinx.android.synthetic.main.item_cocktail.view.*
import kotlinx.android.synthetic.main.item_ingredient.view.*

class AdapterIngredients(val data:List<IngredientItem>): RecyclerView.Adapter<AdapterIngredients.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(
            parent?.inflate(R.layout.item_ingredient)
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bindView(data[position])
    }

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        fun bindView(ingredientItem: IngredientItem){
            with(ingredientItem){
                itemView.txtIngredient.text = ingredient
                itemView.txtMeasure.text = measure

            }

        }

    }
}