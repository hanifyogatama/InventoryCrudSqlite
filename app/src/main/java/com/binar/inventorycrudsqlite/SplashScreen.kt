package com.binar.inventorycrudsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import java.lang.NullPointerException

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException) {
        }

        Handler().postDelayed({
            val intent = Intent(this, OnBoardActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)

    }
}