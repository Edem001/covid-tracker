package com.example.retrofit_practice.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_practice.MainActivity
import com.example.retrofit_practice.MyApplication
import com.example.retrofit_practice.R
import com.example.retrofit_practice.adapters.SearchResultsAdapter
import com.example.retrofit_practice.network.entity.CasesPerCountry
import com.example.retrofit_practice.vm.CasesPerCountryViewModel
import javax.inject.Inject

class CasesPerCountryFragment : Fragment() {
    @Inject
    lateinit var viewModel: CasesPerCountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as MyApplication).appComponent.inject(this)
        (activity as MainActivity).hideNavigation()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        val countryName = arguments?.getString("country") ?: "Ukraine"

        val countryTv = view.findViewById<TextView>(R.id.search_tv_country).apply { text = countryName }
        val scrollRecycler = view.findViewById<RecyclerView>(R.id.search_rv)
        scrollRecycler.animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)

        val dataObserver = Observer<Map<String, CasesPerCountry>> {
            val searchAdapter = SearchResultsAdapter(requireContext(), it)
            scrollRecycler.layoutManager = LinearLayoutManager(requireContext())
            scrollRecycler.adapter = searchAdapter
        }

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val statusObserver = Observer<Boolean> { busy ->
            if (busy) {
                progressBar.animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.animation = AnimationUtils.loadAnimation(context, R.anim.fade_out)
                progressBar.visibility = View.GONE
            }
        }

        viewModel.data.observe(viewLifecycleOwner, dataObserver)
        viewModel.busy.observe(viewLifecycleOwner, statusObserver)
        viewModel.updateData(countryName)

        return view
    }

    override fun onDestroy() {
        (activity as MainActivity).displayNavigation()
        super.onDestroy()
    }
}