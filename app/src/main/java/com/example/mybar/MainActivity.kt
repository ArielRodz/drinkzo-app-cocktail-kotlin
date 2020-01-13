package com.example.mybar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mybar.Detail.DetailActivity
import com.example.mybar.Random.RandomActivity
import com.example.mybar.Welcome.AdapterViewpager
import com.example.mybar.Welcome.Alcoholic.AlcoholicFragment
import com.example.mybar.Welcome.Categories.CategoriesFragment
import com.example.mybar.Welcome.Glasses.GlassesFragment
import com.example.mybar.Welcome.Ingredients.IngredientsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_welcome.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = AdapterViewpager(getSupportFragmentManager())

        var firstFragmet: CategoriesFragment = CategoriesFragment.newInstance()
        var secondFragmet: GlassesFragment = GlassesFragment.newInstance()
        var ThirdFragmet: IngredientsFragment = IngredientsFragment.newInstance()
        var FourFragmet: AlcoholicFragment = AlcoholicFragment.newInstance()

        adapter.addFragment(firstFragmet, "Categories")
        adapter.addFragment(secondFragmet, "Glasses")
        adapter.addFragment(ThirdFragmet, "Ingredients")
         adapter.addFragment(FourFragmet, "Alcoholic")


        vpCategories!!.adapter = adapter

        tabs!!.setupWithViewPager(vpCategories)

        btnRandom.setOnClickListener{
            val intent = Intent(this, RandomActivity::class.java)
            startActivity(intent)

        }


    }
}
