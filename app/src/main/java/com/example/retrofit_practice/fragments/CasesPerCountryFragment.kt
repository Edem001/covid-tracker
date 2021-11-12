package com.example.retrofit_practice.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.retrofit_practice.CovidService
import com.example.retrofit_practice.MainActivity
import com.example.retrofit_practice.MyApplication
import com.example.retrofit_practice.R
import com.example.retrofit_practice.di.DaggerAppComponent
import com.example.retrofit_practice.network.entity.cases.CasesPerCountry
import com.example.retrofit_practice.vm.CasesPerCountryViewModel
import org.w3c.dom.Text
import java.lang.StringBuilder
import java.util.*
import javax.inject.Inject

class CasesPerCountryFragment : Fragment() {
    @Inject
    lateinit var viewModel: CasesPerCountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        (activity as MainActivity).hideNavigation()

        val countryName = arguments?.getString("country") ?: "Ukraine"

        val countryTv = view.findViewById<TextView>(R.id.search_tv_country).apply { text = countryName }
        val scrollTv = view.findViewById<TextView>(R.id.search_tv)

        val observer = Observer<Map<String, CasesPerCountry>> {
            scrollTv.text = it["All"]?.formString()
        }

        viewModel.info.observe(viewLifecycleOwner, observer)
        viewModel.updateData(countryName)

        return view
    }

    override fun onDestroy() {
        (activity as MainActivity).displayNavigation()
        super.onDestroy()
    }
}