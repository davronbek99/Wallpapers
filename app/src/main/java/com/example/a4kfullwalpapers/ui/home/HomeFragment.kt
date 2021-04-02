package com.example.a4kfullwalpapers.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.a4kfullwalpapers.MainActivity
import com.example.a4kfullwalpapers.R
import com.example.a4kfullwalpapers.adapters.ImagePagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    private lateinit var categoryList: ArrayList<String>
    private lateinit var imagePagerAdapter: ImagePagerAdapter
    private lateinit var root: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)
        loadCategories()
        imagePagerAdapter = ImagePagerAdapter(childFragmentManager, categoryList)
        root.view_pager.adapter = imagePagerAdapter
        root.tab_layout.setupWithViewPager(root.view_pager)
        setupTabItems()
        root.tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                val titleCategory = customView?.findViewById<TextView>(R.id.title_category)
                val circleLayout = customView?.findViewById<LinearLayout>(R.id.circle_layout)
                titleCategory?.setTextColor(resources.getColor(R.color.white))
                circleLayout?.visibility = View.VISIBLE
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                val titleCategory = customView?.findViewById<TextView>(R.id.title_category)
                val circleLayout = customView?.findViewById<LinearLayout>(R.id.circle_layout)
                titleCategory?.setTextColor(resources.getColor(R.color.tv_oposity))
                circleLayout?.visibility = View.INVISIBLE
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        return root
    }


    private fun setupTabItems() {
        val tabCount = root.tab_layout.tabCount
        for (i in 0 until tabCount) {
            val tabAt = root.tab_layout.getTabAt(i)
            val tab_view = layoutInflater.inflate(R.layout.tab_item, null, false)
            val title_category = tab_view.findViewById<TextView>(R.id.title_category)
            val circle_layout = tab_view.findViewById<LinearLayout>(R.id.circle_layout)
            title_category.text = categoryList[i]
            if (i == 0) {
                title_category.setTextColor(resources.getColor(R.color.white))
                circle_layout.visibility = View.VISIBLE
            } else {
                title_category.setTextColor(resources.getColor(R.color.tv_oposity))
                circle_layout.visibility = View.INVISIBLE
            }
            tabAt?.customView = tab_view
        }

    }

    private fun loadCategories() {
        categoryList = ArrayList()
        categoryList.add("New")
        categoryList.add("Animal")
        categoryList.add("Sport")
        categoryList.add("Technology")
        categoryList.add("Development")
        categoryList.add("Nature")
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showCircle(1)
    }
}