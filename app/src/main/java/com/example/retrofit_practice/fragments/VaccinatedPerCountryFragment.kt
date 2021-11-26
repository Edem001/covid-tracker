package com.example.retrofit_practice.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.retrofit_practice.MainActivity
import com.example.retrofit_practice.MyApplication
import com.example.retrofit_practice.R
import com.example.retrofit_practice.network.entity.VaccinePerCountry
import com.example.retrofit_practice.vm.VaccinePerCountryViewModel
import java.util.*
import javax.inject.Inject

class VaccinatedPerCountryFragment : Fragment() {

    @Inject
    lateinit var vaccinatedViewModel: VaccinePerCountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as MyApplication).appComponent.inject(this)
        (activity as MainActivity).hideNavigation()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_vaccinated, container, false)
        view.findViewById<TextView>(R.id.vaccinated_tv_country)
            .apply { text = arguments?.getString("country") }

        val regionName = view.findViewById<TextView>(R.id.history_recycler_item_region_name)
            .apply { text = "Summary" }
        val regionInfo = view.findViewById<TextView>(R.id.history_recycler_item_region_info)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        val dataObserver = Observer<Map<String, VaccinePerCountry>> {
            regionInfo.text = it["All"].toString()
        }
        val busyObserver = Observer<Boolean>{ busy ->
            if (busy) {
                view.findViewById<View>(R.id.vaccinated_view_layout).apply {
                    visibility = View.GONE
                }
                progressBar.animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.animation = AnimationUtils.loadAnimation(context, R.anim.fade_out)
                progressBar.visibility = View.GONE

                view.findViewById<View>(R.id.vaccinated_view_layout).apply {
                    animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
                    visibility = View.VISIBLE
                }
            }
        }


        vaccinatedViewModel.timeout.observe(viewLifecycleOwner, {
            if (it){
                Toast.makeText(requireContext(), "Error: connection timeout", Toast.LENGTH_SHORT).show()
            }
        })
        vaccinatedViewModel.busy.observe(viewLifecycleOwner, busyObserver)
        vaccinatedViewModel.vaccinatedData.observe(viewLifecycleOwner, dataObserver)
        vaccinatedViewModel.updateData(arguments?.getString("country") ?: "Ukraine")

        return view
    }

    override fun onDestroy() {
        (activity as MainActivity).displayNavigation()
        super.onDestroy()
    }
}