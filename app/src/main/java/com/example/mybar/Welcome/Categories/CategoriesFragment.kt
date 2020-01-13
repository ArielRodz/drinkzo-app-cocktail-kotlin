package com.example.mybar.Welcome.Categories

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mybar.R
import com.example.mybar.overview.AdapterCockTail
import kotlinx.android.synthetic.main.activity_overview.*
import kotlinx.android.synthetic.main.fragment_category.*

private const val ARG_PARAM1 = "param1"


class CategoriesFragment : Fragment() {


    private val viewModel: CategoriesViewModel by lazy {
        ViewModelProviders.of(this).get(CategoriesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_category, container, false)
        viewModel.responseCategory.observe(this, Observer { listResult ->
            rcFragmentCategory.layoutManager = GridLayoutManager(context, 4)
            val adapter =  AdapterCategories(listResult)
            rcFragmentCategory.adapter = adapter

        })

        viewModel.status.observe(this, Observer { Status ->
            when(Status.name){
                "LOADING" -> print("LOADING")
                "DONE" -> progressBar.visibility = View.GONE
            }
        })

        return view
    }




    companion object {

        @JvmStatic
        fun newInstance() =
            CategoriesFragment().apply {

            }
    }
}
