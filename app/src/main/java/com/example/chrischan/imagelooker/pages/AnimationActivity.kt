package com.example.chrischan.imagelooker.pages

import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.example.chrischan.imagelooker.R

class AnimationActivity: AppCompatActivity() {
    private lateinit var animationImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.animation_layout)
        animationImage = findViewById(R.id.iv_animation)

        animationImage.setOnClickListener {
            ObjectAnimator//
                    .ofFloat(it, "rotationX", 0.0F, 360.0F)//
                    .setDuration(500)//
                    .start()

        }
    }
}