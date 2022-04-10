package com.example.asrbot.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.asrbot.R

@Suppress("DEPRECATION")
class Splash : AppCompatActivity() {

    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        imageView = findViewById<ImageView>(R.id.imageView)

        val rotateAnimation = RotateAnimation(
            0F,
            360F,
            RotateAnimation.RELATIVE_TO_SELF,
            .5f,
            RotateAnimation.RELATIVE_TO_SELF,
            .5f
        )

        rotateAnimation.duration = 3000
        imageView.startAnimation(rotateAnimation)

        Handler().postDelayed({ //This method will be executed once the timer is over
            // Start your app main activity
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            // close this activity
            finish()
        }, 3000)
    }
}