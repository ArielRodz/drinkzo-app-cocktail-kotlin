package com.example.mybar.Welcome.Categories

import android.content.Intent
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.mybar.API.CategoryItem
import com.example.mybar.API.DrinksItem
import com.example.mybar.Detail.DetailActivity
import com.example.mybar.R
import com.example.mybar.Utils.inflate
import com.example.mybar.overview.OverviewActivity
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_cocktail.view.*

class AdapterCategories(val data:List<CategoryItem>): RecyclerView.Adapter<AdapterCategories.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(
            parent?.inflate(R.layout.item_category)
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bindView(data[position])
    }

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        fun bindView(drinkItem: CategoryItem){
            with(drinkItem){
                itemView.txtTitleCategory.text = strCategory


                /*itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("drink",drinkItem )
                    itemView.context.startActivity(intent)
                }*/

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, OverviewActivity::class.java)
                    intent.putExtra("Filter","C" )
                    intent.putExtra("Value",strCategory )

                    itemView.context.startActivity(intent)
                }

            }

        }

    }
}