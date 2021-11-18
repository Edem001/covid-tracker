package com.example.retrofit_practice.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.retrofit_practice.MyApplication
import com.example.retrofit_practice.R
import com.example.retrofit_practice.util.PreferencesWorker
import com.example.retrofit_practice.vm.SummaryViewModel
import java.text.NumberFormat
import javax.inject.Inject

class SummaryFragment : Fragment() {

    @Inject
    lateinit var summaryViewModel: SummaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as MyApplication).appComponent.inject(this)

        summaryViewModel.updateData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_summary, container, false)

        val tvInfected = view.findViewById<TextView>(R.id.tv_cases)
        val tvDeaths = view.findViewById<TextView>(R.id.tv_deaths)

        val updateInfected = Observer<Long>{ tvInfected.text = "Infected: ${NumberFormat.getIntegerInstance().format(it)}" }
        val updateDeaths = Observer<Long> { tvDeaths.text = "Deaths: ${NumberFormat.getIntegerInstance().format(it)}" }

        summaryViewModel.infected.observe(viewLifecycleOwner, updateInfected)
        summaryViewModel.deaths.observe(viewLifecycleOwner, updateDeaths)

        return view
    }

    override fun onResume() {
        super.onResume()
        if (!summaryViewModel.updaterJob.isActive)
            summaryViewModel.updateData()
    }

    override fun onPause() {
        super.onPause()
        if (summaryViewModel.updaterJob.isActive)
            summaryViewModel.interruptUpdating()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (summaryViewModel.updaterJob.isActive)
            summaryViewModel.interruptUpdating()
    }
}