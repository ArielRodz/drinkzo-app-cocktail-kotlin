package com.example.mybar.overview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mybar.R
import kotlinx.android.synthetic.main.activity_overview.*

class OverviewActivity : AppCompatActivity() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)

        val filter = intent.getStringExtra("Filter")
        val value = intent.getStringExtra("Value")
        when(filter) {
            "I"->  viewModel.filterByIngredient(value)
            "G"->  viewModel.filterByGlass(value)
            "C"->  viewModel.filterByCategory(value)
            "A"->  viewModel.filterByAlcoholic(value)



            else -> println("Number too high")
        }
        txtNameOverview.text = value

        viewModel.response.observe(this, Observer { listResult ->
            rcViewLanding.layoutManager = GridLayoutManager(this, 2)
            val adapter =  AdapterCockTail(listResult)
            rcViewLanding.adapter = adapter

        })
        viewModel.status.observe(this, Observer { Status ->
            when(Status.name){
                "LOADING" -> print("LOADING")
                "DONE" -> progressBarOverView.visibility = View.GONE
            }
        })



    }
}
