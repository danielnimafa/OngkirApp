package com.danielnimafa.android.appongkir.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.TextView
import com.danielnimafa.android.appongkir.R
import com.danielnimafa.android.appongkir.model.response.Cost.Rajaongkir
import com.danielnimafa.android.appongkir.utils.CurrencyManager
import com.danielnimafa.android.appongkir.utils.Sout
import com.danielnimafa.android.appongkir.utils.extension.stringGet
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_result_layout.*
import kotlinx.android.synthetic.main.toolbar.*

class TarifResultActivity : AppCompatActivity() {

    companion object {
        operator fun get(context: Context) = Intent(context, TarifResultActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sout.thisContext(this::class.java)
        setContentView(R.layout.activity_result_layout)
        setupView()
        intent.extras?.run {
            val str = getString("ongkir")
            Gson().fromJson(str, Rajaongkir::class.java)?.also { attachData(it) }
        }
    }

    private fun setupView() {

        setSupportActionBar(my_toolbar)
        supportActionBar?.apply {
            title = stringGet(R.string.str_cek_tarif)
            setDisplayHomeAsUpEnabled(true)
        }

        my_toolbar.setNavigationOnClickListener { finish() }
    }

    private fun attachData(t: Rajaongkir) {

        originTx.text = t.origin_details.city_name
        destinationTx.text = t.destination_details.city_name
        weightTx.text = "${t.query.weight}"

        itemCostLayout.removeAllViews()
        t.results[0].costs.forEachIndexed { index, it ->
            val view = layoutInflater.inflate(R.layout.row_itemcost_layout, itemCostLayout,
                    false) as LinearLayout
            val serviceTx: TextView = view.findViewById(R.id.serviceTx)
            val estimationTx: TextView = view.findViewById(R.id.estimationTx)
            val priceTx: TextView = view.findViewById(R.id.priceTx)
            val formattedPrice = CurrencyManager(1).formatPriceRP(it.cost[0].value)

            serviceTx.text = it.service
            estimationTx.text = "${it.cost[0].etd} hari"
            priceTx.text = formattedPrice

            itemCostLayout.addView(view)
        }

    }
}