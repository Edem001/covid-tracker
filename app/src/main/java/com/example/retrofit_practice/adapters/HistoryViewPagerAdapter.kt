package com.example.retrofit_practice.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.retrofit_practice.fragments.HistoryTabFragment
import com.example.retrofit_practice.util.Status

class HistoryViewPagerAdapter(
    val fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    val bundle: Bundle?
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val status = arrayOf(Status.Confirmed, Status.Deaths)

    override fun getItemCount(): Int = status.size

    override fun createFragment(position: Int): Fragment {
        return if (status[position] == Status.Confirmed) {
            HistoryTabFragment.create(status[position], bundle)
        } else {
            HistoryTabFragment.create(status[position], bundle)
        }
    }

}