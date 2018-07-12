package com.danielnimafa.android.appongkir.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.danielnimafa.android.appongkir.R
import com.danielnimafa.android.appongkir.model.content.CityContentModel
import com.danielnimafa.android.appongkir.utils.extension.click
import com.danielnimafa.android.appongkir.utils.extension.ctx

class CitiesAdapter(val datasrc: ArrayList<CityContentModel>,
                    val itemTap: (CityContentModel) -> Unit) : RecyclerView.Adapter<CitiesAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.row_cities_layout, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int = datasrc.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.run {
            datasrc[position].also { t ->
                txCity.text = "${t.city_name} ${t.type}"
                txType.text = t.type
                itemView.click { itemTap(t) }
            }
        }
    }

    class ItemHolder(v: View) : RecyclerView.ViewHolder(v) {

        val txCity: TextView = v.findViewById(R.id.cityTx)
        val txType: TextView = v.findViewById(R.id.typeTx)
    }

}