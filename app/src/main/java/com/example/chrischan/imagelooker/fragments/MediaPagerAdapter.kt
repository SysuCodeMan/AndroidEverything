package com.example.chrischan.imagelooker.fragments

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * @author by Lucifer on 2019/6/5.
 */
class MediaPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment = when(position) {
        0 -> MusicFragment()
        1 -> VideoFragment()
        else -> throw AssertionError()
    }

    override fun getCount(): Int = 2
}