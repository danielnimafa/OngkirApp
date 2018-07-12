package com.danielnimafa.android.appongkir.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.danielnimafa.android.appongkir.R
import com.danielnimafa.android.appongkir.model.content.ProvinceContentModel
import com.danielnimafa.android.appongkir.utils.extension.click
import com.danielnimafa.android.appongkir.utils.extension.ctx

class ProvinceAdapter(val datasrc: ArrayList<ProvinceContentModel>,
                      val itemTap: (ProvinceContentModel) -> Unit) : RecyclerView.Adapter<ProvinceAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.row_province_layout, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int = datasrc.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.run {
            datasrc[position].also { t ->
                txProvince.text = t.province
                itemView.click { itemTap(t) }
            }
        }
    }

    class ItemHolder(v: View) : RecyclerView.ViewHolder(v) {

        val txProvince: TextView = v.findViewById(R.id.provTx)
    }
}