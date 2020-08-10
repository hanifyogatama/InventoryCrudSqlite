package com.binar.inventorycrudsqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.SQLClientInfoException

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        val DB_NAME = "inventory_db"
        val DB_VERSION = 1
        val TABLE_NAME = "InventoryTable"
        val COLUMN_ID = "id"
        val COLUMN_NAME = "nameItem"
        val COLUMN_AMOUNT = "amount"
        val COLUMN_DESCRIPTION = "description"
    }


    override fun onCreate(sqlitedatabase: SQLiteDatabase?) {
        val CREATE_INVENTORIES_TABLE = ("CREATE TABLE $TABLE_NAME(" +
                "$COLUMN_ID INTEGER PRIMARY KEY," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_AMOUNT INTEGER," +
                "$COLUMN_DESCRIPTION TEXT)")
        sqlitedatabase?.execSQL(CREATE_INVENTORIES_TABLE)
    }

    override fun onUpgrade(sqlitedatabase: SQLiteDatabase?, p1: Int, p2: Int) {
        sqlitedatabase?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(sqlitedatabase)
    }

    fun addInventory(inventory: Inventory): Long {
// getdatabase object
        val db = this.writableDatabase

        // use content values to cover data to save in db
        val contentValue = ContentValues()
// contentValue.put(COLUMN_ID,inventory.id)
        contentValue.put(COLUMN_NAME, inventory.nameItem)
        contentValue.put(COLUMN_AMOUNT, inventory.amount)
        contentValue.put(COLUMN_DESCRIPTION, inventory.description)

        // insert to db
        val isSuccess = db.insert(TABLE_NAME, null, contentValue)

        db.close()
        return isSuccess

    }

    fun viewAllInventories(): ArrayList<Inventory> {
// list inventory
        val inventoryList = ArrayList<Inventory>()
        // query select
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase

        // create cursor from inventory table
        var cursor: Cursor? = null
        cursor = db.rawQuery(selectQuery, null, null)

        if (cursor.moveToFirst()) {
            do {
                val idInventory = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val nameInventory = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                val amountInventory = cursor.getInt(cursor.getColumnIndex(COLUMN_AMOUNT))
                val descInventory = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))

                val objectInventory =
                    Inventory(idInventory, nameInventory, amountInventory, descInventory)
                inventoryList.add(objectInventory)
            } while (cursor.moveToNext())
        }
        return inventoryList
    }


    fun updateInventory(inventory: Inventory): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_ID, inventory.id)
        contentValues.put(COLUMN_NAME, inventory.nameItem)
        contentValues.put(COLUMN_AMOUNT, inventory.amount)
        contentValues.put(COLUMN_DESCRIPTION, inventory.description)

        val updateSuccess =
            db.update(TABLE_NAME, contentValues, "$COLUMN_ID =${inventory.id}", null)
        return updateSuccess
    }

    fun deleteInventory(inventory: Inventory): Int {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_ID, inventory.id)
        val deleteSuccess = db.delete(TABLE_NAME, "$COLUMN_ID= ${inventory.id}", null)
        db.close()
        return deleteSuccess
    }

}