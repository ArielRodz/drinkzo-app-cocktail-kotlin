package com.example.mybar.Welcome.Glasses

import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_glass.*



class GlassesFragment : Fragment() {


    private val viewModel: GlassesViewModel by lazy {
        ViewModelProviders.of(this).get(GlassesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_glass, container, false)
        viewModel.responseGlass.observe(this, Observer { listResult ->
            rcFragmentGlass.layoutManager = GridLayoutManager(context, 4)
            val adapter =  AdapterGlasses(listResult)
            rcFragmentGlass.adapter = adapter

        })
        viewModel.status.observe(this, Observer { Status ->
            when(Status.name){
                "LOADING" -> print("LOADING")
                "DONE" -> progressBarGlass.visibility = View.GONE
            }
        })

        return view
    }




    companion object {

        @JvmStatic
        fun newInstance() =
            GlassesFragment().apply {

            }
    }
}
