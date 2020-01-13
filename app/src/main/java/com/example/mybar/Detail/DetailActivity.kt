package com.example.mybar.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mybar.API.DrinksItem
import com.example.mybar.R
import com.example.mybar.Utils.tranformToList
import com.example.mybar.overview.AdapterCockTail
import com.example.mybar.overview.OverviewViewModel
import kotlinx.android.synthetic.main.activity_drink_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_overview.*
import kotlinx.android.synthetic.main.card_drink_detail.*
import kotlinx.android.synthetic.main.card_drink_detail.txtInstructionsDetail
import kotlinx.android.synthetic.main.card_ingr.*
import kotlinx.android.synthetic.main.item_cocktail.view.*

class DetailActivity : AppCompatActivity() {


    private val viewModel: DetailViewModel by lazy {
        ViewModelProviders.of(this).get(DetailViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_detail)

        val id: String = intent.getStringExtra("drink")

        viewModel.findById(id)
        viewModel.response.observe(this, Observer { item ->
            var drinkItem:DrinksItem = item[0]
            Log.d("something",drinkItem.idDrink)
            drinkItem.strDrinkThumb?.let {
                val imgUri = drinkItem.strDrinkThumb!!.toUri().buildUpon().scheme("https").build()
                Glide.with(this)
                    .load(imgUri)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image))
                    .into(imgDetail)
            }

            txtNameDetail.text = drinkItem.strDrink.toString()
            txtInstructionsDetail.text = drinkItem.strInstructions.toString()
            txtAlcoholicDetail.text = drinkItem.strAlcoholic.toString()
            txtGlassDetail.text = drinkItem.strGlass.toString()

            var listIngredients=tranformToList(drinkItem)

            rvIngredients.layoutManager = GridLayoutManager(this, 1)
            val adapter =  AdapterIngredients(listIngredients)
            rvIngredients.adapter = adapter


        })


        viewModel.status.observe(this, Observer { Status ->
            when(Status.name){
                "LOADING" -> print("LOADING")
                "DONE" -> progressBarDetail.visibility = View.GONE
            }
        })





    }
}
