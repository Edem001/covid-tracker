package com.example.retrofit_practice.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_practice.MyApplication
import com.example.retrofit_practice.R
import com.example.retrofit_practice.adapters.HistoryTabRecyclerAdapter
import com.example.retrofit_practice.network.entity.HistoryPerCountry
import com.example.retrofit_practice.util.Status
import com.example.retrofit_practice.vm.HistoryPerCountryViewModel
import javax.inject.Inject

class HistoryTabFragment : Fragment() {

    @Inject
    lateinit var tabViewModel: HistoryPerCountryViewModel
    private lateinit var status: Status
    private var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history_tab, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.history_recycler_tab_rv)
            .apply { animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in) }
        val progressBar = view.findViewById<ProgressBar>(R.id.history_progress_bar)

        val dataObserver = Observer<Map<String, HistoryPerCountry>> {
            recyclerView.adapter = HistoryTabRecyclerAdapter(requireContext(), it)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
        val busyObserver = Observer<Boolean> { busy ->
            if (busy) {
                progressBar.animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.animation = AnimationUtils.loadAnimation(context, R.anim.fade_out)
                progressBar.visibility = View.GONE
            }
        }

        tabViewModel.busy.observe(viewLifecycleOwner, busyObserver)
        if (status == Status.Confirmed) {
            tabViewModel.confirmedData.observe(viewLifecycleOwner, dataObserver)
        } else {
            tabViewModel.deathsData.observe(viewLifecycleOwner, dataObserver)
        }

        tabViewModel.timeout.observe(viewLifecycleOwner, {
            if (it){
                Toast.makeText(requireContext(), "Error: connection timeout", Toast.LENGTH_SHORT).show()
            }
        })

        tabViewModel.updateData(bundle?.getString("country") ?: "Ukraine", status)

        return view
    }

    companion object {
        fun create(status: Status, bundle: Bundle?) = HistoryTabFragment().also {
            it.status = status
            it.bundle = bundle
        }
    }
}