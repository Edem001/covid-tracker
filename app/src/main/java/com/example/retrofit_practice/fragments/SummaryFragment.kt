package com.example.retrofit_practice.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.retrofit_practice.MyApplication
import com.example.retrofit_practice.R
import com.example.retrofit_practice.util.PreferencesWorker
import javax.inject.Inject

class SummaryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_summary, container, false)

        val tvInfected = view.findViewById<TextView>(R.id.tv_cases)
        val tvDeaths = view.findViewById<TextView>(R.id.tv_deaths)


        val prefs = PreferencesWorker(requireContext().getSharedPreferences("covid", Context.MODE_PRIVATE), requireContext())
        tvInfected.text = "Infected: ${prefs.getInfected()}"
        tvDeaths.text = "Deaths: ${prefs.getDeaths()}"

        return view
    }

    override fun onResume() {
        Log.d("Debug", "OnResume()")
        super.onResume()
    }

    override fun onPause() {
        Log.d("Debug", "onPause()")
        super.onPause()
    }
}