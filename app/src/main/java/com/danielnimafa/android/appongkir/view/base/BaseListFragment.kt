package com.danielnimafa.android.appongkir.view.base

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.danielnimafa.android.appongkir.R
import com.danielnimafa.android.appongkir.utils.extension.showAlertMessage
import com.danielnimafa.android.appongkir.view.iface.DataListView
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import kotlinx.android.synthetic.main.list_search_layout.*

abstract class BaseListFragment<V : DataListView, P : MvpBasePresenter<V>> : MvpFragment<V, P>(), DataListView {

    lateinit var mLayoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_search_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupView()
        baseOnActivityCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        baseOnDestroyView()
    }

    override fun showMessage(message: String, title: String?, type: Int) {
        activity?.run {
            when (type) {
                11 -> {
                    showAlertMessage(this, title, message)
                }
                22 -> {
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupView() {

        activity?.run {
            mLayoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
            listview.apply {
                layoutManager = mLayoutManager
                setHasFixedSize(true)
                addItemDecoration(DividerItemDecoration(this@run, mLayoutManager.orientation))
            }
        }
    }

    abstract fun baseOnDestroyView()
    abstract fun baseOnActivityCreated()
}