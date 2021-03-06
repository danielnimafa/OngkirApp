package com.danielnimafa.android.appongkir.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import com.danielnimafa.android.appongkir.R
import com.danielnimafa.android.appongkir.presenter.LoginPresenter
import com.danielnimafa.android.appongkir.presenter.interactor.LoginInteractor
import com.danielnimafa.android.appongkir.utils.Sout
import com.danielnimafa.android.appongkir.utils.extension.*
import com.danielnimafa.android.appongkir.view.iface.LoginView
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.toast

class LoginActivity : MvpActivity<LoginView, LoginPresenter>(), LoginView {

    companion object {
        operator fun get(context: Context) = Intent(context, LoginActivity::class.java)
    }

    var strUname = ""
    var strPass = ""
    var isAllowed = false

    override fun createPresenter(): LoginPresenter = LoginPresenter(LoginInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Sout.thisContext(this::class.java)
        presenter.onCreate()
        setupView()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun setupView() {

        setSupportActionBar(my_toolbar)
        supportActionBar?.apply {
            title = stringGet(R.string.str_login)
        }

        bt_visible.click { showPassword(true) }
        bt_invisible.click { showPassword(false) }

        edUname.onTextChanged {
            strUname = it
            checkInputValue()
        }

        edPassword.onTextChanged {
            strPass = it
            checkInputValue()
        }

        loginBtn.click {
            hideSoftKeyboard()
            postDelayed(120) { presenter.postLoginSubmit() }
        }
    }

    override fun showMessage(message: String, title: String?, type: Int) {
        when (type) {
            11 -> {
                showAlertMessage(this, title, message)
            }
            22 -> {
                toast(message)
            }
        }
    }

    override fun showProgress() {
        pgs.visibility = View.VISIBLE
        edUname.isEnabled = false
        edPassword.isEnabled = false
        loginBtn.isEnabled = false
    }

    override fun hideProgress() {
        pgs.visibility = View.GONE
        edUname.isEnabled = true
        edPassword.isEnabled = true
        checkInputValue()
    }

    private fun showPassword(t: Boolean) {
        bt_visible.visibility = if (t) View.GONE else View.VISIBLE
        bt_invisible.visibility = if (t) View.VISIBLE else View.GONE
        edPassword.transformationMethod = if (t) null else PasswordTransformationMethod()
    }

    private fun checkInputValue() {
        loginBtn.isEnabled = (strUname.isNotEmpty() && strPass.isNotEmpty())
    }
}
