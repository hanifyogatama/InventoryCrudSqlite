package com.binar.inventorycrudsqlite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView


class InventoryAdapter(val listInventory: ArrayList<Inventory>) :
    RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.inventory_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
return listInventory.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}