package com.example.mybar.Random

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
import com.example.mybar.Detail.AdapterIngredients
import com.example.mybar.Detail.DetailViewModel
import com.example.mybar.R
import com.example.mybar.Utils.tranformToList
import kotlinx.android.synthetic.main.activity_drink_detail.*
import kotlinx.android.synthetic.main.activity_random.*
import kotlinx.android.synthetic.main.card_drink_detail.*
import kotlinx.android.synthetic.main.card_drink_detail.txtInstructionsDetail
import kotlinx.android.synthetic.main.card_img.*
import kotlinx.android.synthetic.main.card_ingr.*
import kotlinx.android.synthetic.main.card_welcome.*

class RandomActivity : AppCompatActivity() {


    private val viewModel: RandomViewModel by lazy {
        ViewModelProviders.of(this).get(RandomViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        viewModel.response.observe(this, Observer { item ->
            var drinkItem: DrinksItem = item[0]
            Log.d("something",drinkItem.idDrink)
            drinkItem.strDrinkThumb?.let {
                val imgUri = drinkItem.strDrinkThumb!!.toUri().buildUpon().scheme("https").build()
                Glide.with(this)
                    .load(imgUri)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image))
                    .into(imgRandom)
            }

            txtNameDetail.text = drinkItem.strDrink.toString()
            txtInstructionsDetail.text = drinkItem.strInstructions.toString()
            txtAlcoholicDetail.text = drinkItem.strAlcoholic.toString()
            txtGlassDetail.text = drinkItem.strGlass.toString()

            var listIngredients= tranformToList(drinkItem)

            rvIngredients.layoutManager = GridLayoutManager(this, 1)
            val adapter =  AdapterIngredients(listIngredients)
            rvIngredients.adapter = adapter


        })


        viewModel.status.observe(this, Observer { Status ->
            when(Status.name){
                "LOADING" -> print("LOADING")
                "DONE" -> progressBarRandom.visibility = View.GONE
            }
        })


        btnNewRandom.setOnClickListener{

            viewModel.getRandom()
        }
    }
}
