package com.example.printshop.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.printshop.R
import com.example.printshop.ui.base.AppActivity
import com.example.printshop.ui.base.AuthActivity
import com.example.printshop.ui.base.MasterActivity


class SplashActivity : AppActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        super.SetStatusTransparent()

        Handler().postDelayed({
            val mainIntent = Intent(this, AuthActivity::class.java)
            startActivity(mainIntent)
             finish()
        }, 3000)
    }
}
