package com.example.chrischan.imagelooker.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chrischan.imagelooker.R

/**
 * @author by Lucifer on 2019/6/5.
 */
class MusicFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.music_fragment_layout, container, false)
    }
}