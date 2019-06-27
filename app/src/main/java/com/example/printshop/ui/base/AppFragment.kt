package com.example.printshop.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast

open class AppFragment:Fragment(),VIEW {

    override fun ShowNotification(message: String) {
        Toast.makeText(_authActivity,message,Toast.LENGTH_LONG).show()
    }

     var _authActivity:AuthActivity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _authActivity = activity as AuthActivity
    }

    override fun ShowLoading() {
        _authActivity?.ShowLoading()
    }

    override fun HideLoading() {
        _authActivity?.HideLoading()
    }
}