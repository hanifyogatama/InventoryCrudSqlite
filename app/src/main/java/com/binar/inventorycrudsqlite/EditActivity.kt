package com.binar.inventorycrudsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException) {
        }

        val dbHandler = DatabaseHandler(this)
        val inventory = intent.getParcelableExtra<Inventory>("inventory")

        supportActionBar?.title = "ubah data ${inventory?.nameItem}"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        edNameItem.setText(inventory?.nameItem)
        edAmoutItem.setText(inventory?.amount.toString())
        edDescItem.setText(inventory?.description)

        btnSave.setOnClickListener {
            inventory?.nameItem = edNameItem.text.toString()
            inventory?.amount = edAmoutItem.text.toString().toInt()
            inventory?.description = edDescItem.text.toString()

            inventory?.let {

                if (dbHandler.updateInventory(it) != 0) {
                    Toast.makeText(this, "data Berhasil Diubah", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "gagal diubah", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}