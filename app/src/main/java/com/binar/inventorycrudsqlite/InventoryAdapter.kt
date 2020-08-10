package com.binar.inventorycrudsqlite

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.inventory_item.view.*


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
        holder.itemView.tvID.text = listInventory[position].id.toString()
        holder.itemView.tvNameItem.text = listInventory[position].nameItem
        holder.itemView.tvAmount.text = listInventory[position].amount.toString()
        holder.itemView.tvDesc.text = listInventory[position].description

        holder.itemView.ivEdit.setOnClickListener {
            val intentKeEditActivity = Intent(it.context, EditActivity::class.java)
            intentKeEditActivity.putExtra("inventory", listInventory[position])
            it.context.startActivity(intentKeEditActivity)
        }

        holder.itemView.ivDelete.setOnClickListener {
            AlertDialog.Builder(it.context).setPositiveButton("Ya") { p0, p1 ->
                val dbHelper = DatabaseHandler(holder.itemView.context)
                if (dbHelper.deleteInventory(listInventory[position]) != 0) {
                    Toast.makeText(
                        it.context, "Data ${listInventory[position].nameItem} berhasil dihapus",
                        Toast.LENGTH_LONG
                    ).show()
                    (it.context as MainActivity).fetchData()
                } else {
                    Toast.makeText(
                        it.context, "Data ${listInventory[position].nameItem} Gagal dihapus",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }.setNegativeButton(
                "Tidak"
            ) { p0, p1 ->
                p0.dismiss()
            }
                .setMessage("Apakah Anda Yakin ingin menghapus data ${listInventory[position].nameItem}")
                .setTitle("Konfirmasi Hapus").create().show()
        }
    }
}