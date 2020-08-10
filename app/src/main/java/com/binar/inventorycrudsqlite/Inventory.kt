package com.binar.inventorycrudsqlite

import android.os.Parcel
import android.os.Parcelable

class Inventory(val id: Int?, var nameItem: String, var amount: Int, var description: String) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(nameItem)
        parcel.writeInt(amount)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Inventory> {
        override fun createFromParcel(parcel: Parcel): Inventory {
            return Inventory(parcel)
        }

        override fun newArray(size: Int): Array<Inventory?> {
            return arrayOfNulls(size)
        }
    }

}


