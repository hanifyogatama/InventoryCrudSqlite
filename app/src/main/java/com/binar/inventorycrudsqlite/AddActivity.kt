package com.binar.inventorycrudsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException) {
        }

        val dbHandler = DatabaseHandler(this)
        btnSave.setOnClickListener {
            val inventory = Inventory(
                null,
                edNameItem.text.toString(),
                edAmoutItem.text.toString().toInt(),
                edDescItem.text.toString()
            )

            if (dbHandler.addInventory(inventory) != 0.toLong()) {
                Toast.makeText(this, "Data sukses disimpan", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "gagal disimpan", Toast.LENGTH_LONG).show()
            }
        }
    }
}