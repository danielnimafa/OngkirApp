package com.danielnimafa.android.appongkir.view

import android.os.Bundle
import android.support.v4.app.Fragment
import com.danielnimafa.android.appongkir.presenter.ProvincePresenter
import com.danielnimafa.android.appongkir.presenter.interactor.ProvinceInteractor
import com.danielnimafa.android.appongkir.utils.Sout
import com.danielnimafa.android.appongkir.view.base.BaseListFragment
import com.danielnimafa.android.appongkir.view.iface.DataListView

class ProvinceFragment : BaseListFragment<DataListView, ProvincePresenter>() {

    companion object {
        val TAG = this::class.java.simpleName
        fun newInstance(): Fragment {
            val bundle = Bundle()
            return ProvinceFragment().apply { arguments = bundle }
        }
    }

    override fun createPresenter(): ProvincePresenter = ProvincePresenter(ProvinceInteractor())

    override fun baseOnActivityCreated() {
        Sout.thisContext(this::class.java)
        presenter.onCreate()
    }

    override fun baseOnDestroyView() {
        presenter.onDestroy()
    }

    override fun showList(list: List<Any>) {

    }
}