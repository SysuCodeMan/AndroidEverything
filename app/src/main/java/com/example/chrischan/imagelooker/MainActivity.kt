package com.example.chrischan.imagelooker

import android.animation.FloatEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

/**
 * @author by Lucifer on 2019/1/19.
 */
class MainActivity : AppCompatActivity() {
    private var mFrameImageView: ImageView? = null
    private var mTweenImageView: ImageView? = null
    private var mPropertyImageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        initViews()
    }

    private fun initViews() {
        mFrameImageView = findViewById(R.id.frame_demo)
        val animationDrawable = resources.getDrawable(R.drawable.frame_animation) as AnimationDrawable
        mFrameImageView!!.setImageDrawable(animationDrawable)
        animationDrawable.start()

        mTweenImageView = findViewById(R.id.tween_demo)
        val alphaAnimation = AnimationUtils.loadAnimation(this,
                R.anim.tween_alpha_animation)
        mTweenImageView!!.animation = alphaAnimation

        mPropertyImageView = findViewById(R.id.property_demo)
        //        final ValueAnimator alphaAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        //        alphaAnimator.setDuration(2000);
        //        alphaAnimator.setEvaluator(new FloatEvaluator());
        //        alphaAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //        alphaAnimator.setRepeatCount(ValueAnimator.INFINITE);
        //        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
        //            @Override
        //            public void onAnimationUpdate(ValueAnimator animation) {
        //                mPropertyImageView.setAlpha((float)alphaAnimator.getAnimatedValue());
        //            }
        //        });
        //        alphaAnimator.start();
        val objectAnimator = ObjectAnimator.ofFloat(mPropertyImageView, "alpha", 0.0f,
                1.0f)
        objectAnimator.duration = 2000
        objectAnimator.setEvaluator(FloatEvaluator())
        objectAnimator.repeatMode = ValueAnimator.RESTART
        objectAnimator.repeatCount = ValueAnimator.INFINITE
        objectAnimator.start()

    }
}
