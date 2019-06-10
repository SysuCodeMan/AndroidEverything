package com.example.chrischan.imagelooker.pages

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import com.example.chrischan.imagelooker.R
import com.example.chrischan.imagelooker.fragments.MediaPagerAdapter

/**
 * @author by Lucifer on 2019/5/9.
 */
class MediaPlayActivity: FragmentActivity() {
    private lateinit var mPager: ViewPager
    private lateinit var mTabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.media_layout)

        mPager = findViewById(R.id.media_pager)
        mTabLayout = findViewById(R.id.media_tab)

        val pagerAdapter = MediaPagerAdapter(supportFragmentManager)
        mPager.adapter = pagerAdapter
        mTabLayout.setupWithViewPager(mPager)
    }
}