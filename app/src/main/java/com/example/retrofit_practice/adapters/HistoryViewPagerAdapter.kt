package com.example.retrofit_practice.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.retrofit_practice.fragments.HistoryTabFragment
import com.example.retrofit_practice.util.Status

class HistoryViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    private val status = arrayOf(Status.Confirmed, Status.Deaths)

    override fun getItemCount(): Int = status.size

    override fun createFragment(position: Int): Fragment = HistoryTabFragment.create(status[position])

}