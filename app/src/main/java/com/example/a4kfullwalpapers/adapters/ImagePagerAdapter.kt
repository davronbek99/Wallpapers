package com.example.a4kfullwalpapers.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.a4kfullwalpapers.ui.home.ImageFragment

class ImagePagerAdapter(fragmentManager: FragmentManager, var categoryList: List<String>) :
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return categoryList.size
    }

    override fun getItem(position: Int): Fragment {
        return ImageFragment.newInstance(categoryList[position])
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }
}