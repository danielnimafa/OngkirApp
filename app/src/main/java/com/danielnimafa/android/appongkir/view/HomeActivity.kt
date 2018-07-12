package com.danielnimafa.android.appongkir.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import com.danielnimafa.android.appongkir.R
import com.danielnimafa.android.appongkir.model.event.OriginCityEvent
import com.danielnimafa.android.appongkir.presenter.HomePresenter
import com.danielnimafa.android.appongkir.presenter.interactor.HomeInteractor
import com.danielnimafa.android.appongkir.utils.Sout
import com.danielnimafa.android.appongkir.utils.extension.click
import com.danielnimafa.android.appongkir.utils.extension.postDelayed
import com.danielnimafa.android.appongkir.utils.extension.showAlertSingleActionMessage
import com.danielnimafa.android.appongkir.utils.extension.stringGet
import com.danielnimafa.android.appongkir.view.iface.HomeView
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.hwangjr.rxbus.RxBus
import com.hwangjr.rxbus.annotation.Subscribe
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.circle_progress_layout.*
import kotlinx.android.synthetic.main.message_info_layout.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.toast

class HomeActivity : MvpActivity<HomeView, HomePresenter>(), HomeView {

    companion object {
        operator fun get(context: Context) = Intent(context, HomeActivity::class.java)
    }

    override fun createPresenter() = HomePresenter(HomeInteractor())

    var backState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sout.thisContext(this::class.java)
        setContentView(R.layout.activity_home)
        setupView()
        RxBus.get().register(this)
        presenter.onCreate(this)
        loadSourceData()
    }

    override fun onDestroy() {
        super.onDestroy()
        RxBus.get().unregister(this)
        presenter.onDestroy()
    }

    override fun onBackPressed() {
        if (backState) {
            super.onBackPressed()
            return
        }

        backState = true
        toast("Tekan sekali lagi untuk keluar")
        postDelayed(2000) { backState = false }
    }

    override fun gotoDetailScreen(str: String) {
        startActivity(TarifResultActivity[this].apply { putExtra("ongkir", str) })
    }

    override fun updateButtonMessage(message: String) {
        checkBtn.text = message
    }

    override fun showPopupMessage(title: String, message: String) {
        showAlertSingleActionMessage(this, message, title,
                { dialogInterface, i -> dialogInterface.dismiss() })
    }

    override fun showTarifLayout(state: Boolean) {
        contentLayout.visibility = if (state) View.VISIBLE else View.GONE
    }

    override fun showMessageScreen(message: String, showReloadButton: Boolean) {
        messageInfoTx.visibility = View.VISIBLE
        messageInfoTx.text = message
        reloadBtn.visibility = if (showReloadButton) View.VISIBLE else View.GONE
    }

    override fun showProgressCost(message: String) {
        radio_jne.isEnabled = false
        radio_tiki.isEnabled = false
        radio_pos.isEnabled = false
        edWeight.isEnabled = false
        originBtn.isEnabled = false
        destinationBtn.isEnabled = false
        resetInputBtn.isEnabled = false
        checkBtn.isEnabled = false
        updateButtonMessage(message)
    }

    override fun hideProgressCost() {
        radio_jne.isEnabled = true
        radio_tiki.isEnabled = true
        radio_pos.isEnabled = true
        edWeight.isEnabled = true
        originBtn.isEnabled = true
        destinationBtn.isEnabled = true
        resetInputBtn.isEnabled = true
        checkBtn.isEnabled = true
        updateButtonMessage(stringGet(R.string.str_cek_tarif))
    }

    override fun showProgress() {
        circleProgress.visibility = View.VISIBLE
        reloadBtn.visibility = View.GONE
        messageInfoTx.visibility = View.GONE
        showTarifLayout(false)
    }

    override fun restoreDefaultInputState() {
        edOrigin.setText("")
        edDestination.setText("")
        edWeight.setText("")
        radio_jne.isChecked = true
    }

    override fun tos(message: String) = toast(message)

    override fun hideProgress() {
        circleProgress.visibility = View.GONE
    }

    private fun loadSourceData() {
        presenter.loadSourceData()
    }

    private fun setupView() {

        setSupportActionBar(my_toolbar)
        supportActionBar?.apply {
            title = stringGet(R.string.app_name)
        }

        showTarifLayout(false)

        originBtn.click { }
        destinationBtn.click { }
//        originClearBtn.click { edOrigin.setText(""); presenter.assignOriginCity("") }
//        destinationClearBtn.click { edDestination.setText(""); presenter.assignDestinationCity("") }
        reloadBtn.click { loadSourceData() }
        checkBtn.click { presenter.validateInput() }
        resetInputBtn.click { presenter.resetInputAction() }
        radio_jne.click { selectCourier(it) }
        radio_tiki.click { selectCourier(it) }
        radio_pos.click { selectCourier(it) }
    }

    private fun selectCourier(it: View) {

        val checked = (it as RadioButton).isChecked
        when (it.id) {
            R.id.radio_jne -> {
                if (checked) presenter.assignCourerValue(HomePresenter.JNE)
            }
            R.id.radio_tiki -> {
                if (checked) presenter.assignCourerValue(HomePresenter.TIKI)
            }
            R.id.radio_pos -> {
                if (checked) presenter.assignCourerValue(HomePresenter.POS)
            }
        }
    }

    @Subscribe
    fun selectedOriginCity(t: OriginCityEvent) {
        Sout.log("selected City", "${t.cityName}, ${t.cityId}")
        presenter.assignOriginCity(t.cityId)
    }

    @Subscribe
    fun selectedDestinationCity(t: OriginCityEvent) {
        Sout.log("selected City", "${t.cityName}, ${t.cityId}")
        presenter.assignDestinationCity(t.cityId)
    }

}
