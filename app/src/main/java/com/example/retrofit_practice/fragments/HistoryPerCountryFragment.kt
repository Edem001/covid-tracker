package com.example.retrofit_practice.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.retrofit_practice.MainActivity
import com.example.retrofit_practice.MyApplication
import com.example.retrofit_practice.R

class HistoryPerCountryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.application as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        (activity as MainActivity).hideNavigation()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        val viewPager = view.findViewById<ViewPager2>(R.id.history_view_pager)

        return view
    }

    override fun onDestroy() {
        (activity as MainActivity).displayNavigation()
        super.onDestroy()
    }
}