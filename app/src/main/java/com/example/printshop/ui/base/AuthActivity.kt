package com.example.printshop.ui.base

import android.net.Uri
import android.os.Bundle
import com.example.printshop.R
import com.example.printshop.di.component.AuthComponent
import com.example.printshop.di.component.DaggerAuthComponent
import com.example.printshop.di.module.AuthModule
import com.example.printshop.domain.App
import com.example.printshop.ui.auth.views.RegisterFragment

class AuthActivity : AppActivity(), RegisterFragment.OnFragmentInteractionListener {

     var  _authComponent:AuthComponent? = null

    override fun onFragmentInteraction(uri: Uri) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        SetStatusTransparent()
        TransitionToFragment(RegisterFragment())

        _authComponent = DaggerAuthComponent.builder()
                .appComponent((application as App).appComponent)
                .authModule(AuthModule(this))
                .build()
    }
}
