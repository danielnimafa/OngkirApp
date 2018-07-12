package com.danielnimafa.android.appongkir.view

import android.os.Bundle
import android.support.v4.app.Fragment
import com.danielnimafa.android.appongkir.R
import com.danielnimafa.android.appongkir.adapter.ProvinceAdapter
import com.danielnimafa.android.appongkir.model.content.ProvinceContentModel
import com.danielnimafa.android.appongkir.presenter.ProvincePresenter
import com.danielnimafa.android.appongkir.presenter.interactor.ProvinceInteractor
import com.danielnimafa.android.appongkir.utils.Sout
import com.danielnimafa.android.appongkir.utils.extension.stringGet
import com.danielnimafa.android.appongkir.view.base.BaseListFragment
import com.danielnimafa.android.appongkir.view.iface.DataListView
import com.danielnimafa.android.appongkir.view.iface.RegionScreenListener
import kotlinx.android.synthetic.main.list_search_layout.*

class ProvinceFragment : BaseListFragment<DataListView, ProvincePresenter>() {

    companion object {
        val TAG = this::class.java.simpleName
        fun newInstance(): Fragment {
            val bundle = Bundle()
            return ProvinceFragment().apply { arguments = bundle }
        }
    }

    var listener: RegionScreenListener? = null
    lateinit var mAdapter: ProvinceAdapter

    override fun createPresenter(): ProvincePresenter = ProvincePresenter(ProvinceInteractor())

    override fun baseOnActivityCreated() {
        Sout.thisContext(this::class.java)
        activity?.run { listener = this as RegionScreenListener }
        setupTitleScreen()
        presenter.onCreate()
    }

    override fun baseOnDestroyView() {
        presenter.onDestroy()
    }

    override fun showList(list: List<Any>) {
        mAdapter = ProvinceAdapter(list as ArrayList<ProvinceContentModel>, { onItemTap(it) })
        listview.adapter = mAdapter
    }

    private fun onItemTap(it: ProvinceContentModel) {
        listener?.gotoCityScreen(it)
    }

    private fun setupTitleScreen() {
        val title = activity?.stringGet(R.string.str_province)
        listener?.setupTitleScreen(title ?: "-")
    }
}