package com.example.mybar.overview

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

class AdapterCockTail(val data:List<DrinksItem>): RecyclerView.Adapter<AdapterCockTail.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(
            parent?.inflate(R.layout.item_cocktail)
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bindView(data[position])
    }

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        fun bindView(drinkItem: DrinksItem){
            with(drinkItem){
                itemView.txtTitleItem.text = strDrink

                strDrinkThumb?.let {
                    val imgUri = strDrinkThumb.toUri().buildUpon().scheme("https").build()
                    Glide.with(itemView.context)
                        .load(imgUri)
                        .apply(
                            RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image))
                        .into(itemView.imageItemHeader)
                }


                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("drink",drinkItem.idDrink )
                    itemView.context.startActivity(intent)
                }


            }

        }

    }
}