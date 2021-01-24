package com.kotlin.trifork.marvelapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(
    var mFragmentList: List<Fragment>,
    var mFragmentTitleList: List<String>,
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int) = mFragmentList[position]

    override fun getCount() = mFragmentList.size

    override fun getPageTitle(position: Int) = mFragmentTitleList[position]

}