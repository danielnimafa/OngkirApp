package com.danielnimafa.android.appongkir.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.danielnimafa.android.appongkir.R
import com.danielnimafa.android.appongkir.model.content.ProvinceContentModel
import com.danielnimafa.android.appongkir.utils.Sout
import com.danielnimafa.android.appongkir.view.iface.RegionScreenListener
import kotlinx.android.synthetic.main.toolbar.*

class RegionActivity : AppCompatActivity(), RegionScreenListener {

    companion object {
        operator fun get(context: Context) = Intent(context, RegionActivity::class.java)
    }

    lateinit var fm: FragmentManager
    lateinit var cityType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sout.thisContext(this::class.java)
        setContentView(R.layout.activity_region_layout)
        setupView()
        intent.extras?.run { cityType = getString("type") }
    }

    override fun onBackPressed() {
        val count = fm.backStackEntryCount
        if (count > 0) fm.popBackStack() else super.onBackPressed()
    }

    override fun setupTitleScreen(title: String) {
        supportActionBar?.title = title
    }

    override fun setupSubtitleScreen(subtitle: String) {
        supportActionBar?.subtitle = subtitle
    }

    override fun grabCityType(): String = cityType

    override fun closeActivity() = finish()

    override fun gotoCityScreen(t: ProvinceContentModel) {
        fm.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .addToBackStack(ProvinceFragment.TAG)
                .replace(R.id.contentLayout, CitiesFragment.newInstance(t.provinceId, t.province), CitiesFragment.TAG)
                .commit()
    }

    private fun setupView() {

        setSupportActionBar(my_toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        my_toolbar.setNavigationOnClickListener { onBackPressed() }

        fm = supportFragmentManager

        fm.beginTransaction()
                .add(R.id.contentLayout, ProvinceFragment.newInstance(), ProvinceFragment.TAG)
                .commit()
    }
}