package com.example.chrischan.imagelooker.pages

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.chrischan.imagelooker.R
import com.example.chrischan.imagelooker.pages.ndk.NativeLibrary

class NdkActivity: AppCompatActivity() {
    private lateinit var ndkText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ndk_layout)
        ndkText = findViewById(R.id.tv_ndk)
        ndkText.text = NativeLibrary().string
    }
}