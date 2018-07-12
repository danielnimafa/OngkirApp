package com.danielnimafa.android.appongkir.view

import android.os.Bundle
import android.support.v4.app.Fragment
import com.danielnimafa.android.appongkir.presenter.CitiesPresenter
import com.danielnimafa.android.appongkir.presenter.interactor.CitiesInteractor
import com.danielnimafa.android.appongkir.utils.Sout
import com.danielnimafa.android.appongkir.view.base.BaseListFragment
import com.danielnimafa.android.appongkir.view.iface.DataListView
import com.danielnimafa.android.appongkir.view.iface.RegionScreenListener

class CitiesFragment : BaseListFragment<DataListView, CitiesPresenter>(), DataListView {

    companion object {
        val TAG = this::class.java.simpleName
        fun newInstance(provinceId: String?, province: String?): Fragment {
            val bundle = Bundle().apply {
                putString("id", provinceId)
                putString("name", province)
            }
            return CitiesFragment().apply { arguments = bundle }
        }
    }

    var listener: RegionScreenListener? = null
    lateinit var provinceID: String
    lateinit var province: String

    override fun createPresenter(): CitiesPresenter = CitiesPresenter(CitiesInteractor())

    override fun baseOnActivityCreated() {
        Sout.thisContext(this::class.java)
        activity?.run { listener = this as RegionScreenListener }
        arguments?.run {
            provinceID = getString("id")
            province = getString("name")
            setupTitleScreen()
        }
        presenter.onCreate()
    }

    override fun baseOnDestroyView() {
        presenter.onDestroyView()
    }

    override fun showList(list: List<Any>) {

    }

    private fun setupTitleScreen() {
        listener?.setupTitleScreen(province)
    }
}