package com.example.retrofit_practice.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.retrofit_practice.MainActivity
import com.example.retrofit_practice.MyApplication
import com.example.retrofit_practice.R
import com.example.retrofit_practice.adapters.HistoryViewPagerAdapter
import com.example.retrofit_practice.util.DepthPageTransformer
import com.example.retrofit_practice.vm.HistoryPerCountryViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class HistoryPerCountryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).hideNavigation()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        val textView = view.findViewById<TextView>(R.id.history_tv_country).apply {
            text = arguments?.getString("country") ?: ""
        }
        val viewPager = view.findViewById<ViewPager2>(R.id.history_view_pager)
        val viewPagerAdapter = HistoryViewPagerAdapter(parentFragmentManager, viewLifecycleOwner.lifecycle, arguments)
        viewPager.adapter = viewPagerAdapter
        viewPager.setPageTransformer(DepthPageTransformer())
        viewPager.offscreenPageLimit = 1

        val tabLayout = view.findViewById<TabLayout>(R.id.history_tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            if (position == 0)
                tab.text = "Confirmed"
            else
                tab.text = "Deaths"
        }.attach()

        return view
    }

    override fun onDestroy() {
        (activity as MainActivity).displayNavigation()
        super.onDestroy()
    }
}