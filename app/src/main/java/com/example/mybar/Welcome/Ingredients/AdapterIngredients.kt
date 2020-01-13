package com.example.mybar.Welcome.Ingredients

import android.content.Intent
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.mybar.API.CategoryItem
import com.example.mybar.API.DrinksItem
import com.example.mybar.API.GlassItem
import com.example.mybar.API.IngredientItem
import com.example.mybar.Detail.DetailActivity
import com.example.mybar.R
import com.example.mybar.Utils.inflate
import com.example.mybar.overview.OverviewActivity
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_cocktail.view.*

class AdapterIngredients(val data:List<IngredientItem>): RecyclerView.Adapter<AdapterIngredients.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(
            parent?.inflate(R.layout.item_category)
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bindView(data[position])
    }

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        fun bindView(drinkItem: IngredientItem){
            with(drinkItem){
                itemView.txtTitleCategory.text = strIngredient1


                /*itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("drink",drinkItem )
                    itemView.context.startActivity(intent)
                }*/

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, OverviewActivity::class.java)
                    intent.putExtra("Filter","I" )
                    intent.putExtra("Value",strIngredient1 )

                    itemView.context.startActivity(intent)
                }


            }

        }

    }
}