package com.example.laundryappproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class ViewPagerAdapter(private val context: Context): PagerAdapter() {
    private val descs = arrayOf(R.string.desc1_welcome, R.string.desc2_welcome)

    override fun getCount(): Int {
        return 2
    }

    override fun isViewFromObject(view: View, objects: Any): Boolean {
        return view == objects
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = LayoutInflater.from(container.context).inflate(R.layout.slider_layout,container, false)
        val slideDescription: TextView = view.findViewById(R.id.tvDescription)
        slideDescription.text = context.resources.getString(descs[position])
        val viewPager: ViewPager = container as ViewPager
        viewPager.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, objects: Any) {
        val viewPager: ViewPager = container as ViewPager
        val view: View = objects as View
        viewPager.removeView(view)
    }
}