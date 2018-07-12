package com.danielnimafa.android.appongkir.view

import android.os.Bundle
import android.support.v4.app.Fragment
import com.danielnimafa.android.appongkir.R
import com.danielnimafa.android.appongkir.adapter.CitiesAdapter
import com.danielnimafa.android.appongkir.model.content.CityContentModel
import com.danielnimafa.android.appongkir.model.event.DestinationCityEvent
import com.danielnimafa.android.appongkir.model.event.OriginCityEvent
import com.danielnimafa.android.appongkir.presenter.CitiesPresenter
import com.danielnimafa.android.appongkir.presenter.interactor.CitiesInteractor
import com.danielnimafa.android.appongkir.utils.Sout
import com.danielnimafa.android.appongkir.utils.extension.postDelayed
import com.danielnimafa.android.appongkir.utils.extension.stringGet
import com.danielnimafa.android.appongkir.view.base.BaseListFragment
import com.danielnimafa.android.appongkir.view.iface.DataListView
import com.danielnimafa.android.appongkir.view.iface.RegionScreenListener
import com.hwangjr.rxbus.RxBus
import kotlinx.android.synthetic.main.list_search_layout.*

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
    lateinit var mAdapter: CitiesAdapter

    override fun createPresenter(): CitiesPresenter = CitiesPresenter(CitiesInteractor())

    override fun baseOnActivityCreated() {
        Sout.thisContext(this::class.java)
        activity?.run { listener = this as RegionScreenListener }
        arguments?.run {
            provinceID = getString("id")
            province = getString("name")
            setupTitleScreen()
        }
        presenter.onCreate(provinceID)
    }

    override fun baseOnDestroyView() {
        presenter.onDestroyView()
    }

    override fun showList(list: List<Any>) {
        mAdapter = CitiesAdapter(list as ArrayList<CityContentModel>, { onitemTap(it) })
        listview.adapter = mAdapter
    }

    private fun onitemTap(it: CityContentModel) {
        activity?.run {
            postDelayed(200) {
                val cityType = listener?.grabCityType()
                var event: Any
                cityType?.also { s ->
                    if (s == "origin") {
                        event = OriginCityEvent().apply {
                            cityId = it.city_id ?: "null"
                            cityName = "${it.type} ${it.city_name}"
                        }
                    } else {
                        event = DestinationCityEvent().apply {
                            cityId = it.city_id ?: "null"
                            cityName = "${it.type} ${it.city_name}"
                        }
                    }
                    RxBus.get().post(event)
                    listener?.closeActivity()
                }
            }
        }
    }

    private fun setupTitleScreen() {
        listener?.run {
            setupTitleScreen(activity?.stringGet(R.string.str_choose_city) ?: "null")
            setupSubtitleScreen(province)
        }

    }
}