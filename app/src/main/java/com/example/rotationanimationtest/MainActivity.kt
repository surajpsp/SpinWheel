package com.example.rotationanimationtest

import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    private val sectors: List<String> = listOf("1", "2", "3", "4", "5", "6")
    private val sectorsDegree: MutableList<Int> = mutableListOf()
    private lateinit var image: ImageView
    private var isSpinnerPlaying: Boolean = false

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById(R.id.rotateMe)
        setDegrees()

        image.setOnClickListener {
            if (isSpinnerPlaying.not()) {
                setupSpinner()
            }
        }


    }

    private fun setupSpinner() {
        isSpinnerPlaying = true
        val randomDegree = 2
        val from = 0f
        val to = (360 * sectors.size).plus(sectorsDegree[randomDegree])
        val rotateAnimation: RotateAnimation = RotateAnimation(
            from,
            to.toFloat(),
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f,
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f
        )
        rotateAnimation.duration = 5000
        rotateAnimation.fillAfter = true
        rotateAnimation.interpolator = DecelerateInterpolator()
        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                Log.d(TAG, "onAnimationStart: degree - $p0")
            }

            override fun onAnimationEnd(p0: Animation?) {
                Log.d(TAG, "onAnimationEnd: You have won - ${sectorsDegree[2]}")
                Log.d(TAG, "onAnimationEnd: degree $p0")
                isSpinnerPlaying = false
            }

            override fun onAnimationRepeat(p0: Animation?) {
                Log.d(TAG, "onAnimationRepeat: ${p0?.repeatCount}")
            }
        })
        image.startAnimation(rotateAnimation)
    }

    private fun setDegrees() {
        val perSectorDegree = 360 / sectors.size

        for (i in 0..sectors.size.minus(1)) {
            val degree = (i.plus(1)).times(perSectorDegree)
            sectorsDegree.add(i, degree)
        }
    }


}