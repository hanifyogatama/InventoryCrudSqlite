package com.binar.inventorycrudsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException) {
        }
    }
}