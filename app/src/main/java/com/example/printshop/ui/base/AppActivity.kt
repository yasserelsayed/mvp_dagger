package com.example.printshop.ui.base

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.printshop.R
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.custom_loading.*


open class AppActivity : AppCompatActivity() {


    var CurrentFragment:Fragment? = null

    fun SetStatusTransparent() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

     fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
        val win = activity.window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }


    fun TransitionToFragment(NewFragment: Fragment) {
        this.CurrentFragment = NewFragment
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.content_fragment_container, NewFragment)
        ft.addToBackStack(null)
        ft.commit()
    }

    fun ShowLoading() {
        rlt_loader?.setVisibility(View.VISIBLE)
    }

    fun HideLoading() {
        rlt_loader?.setVisibility(View.GONE)
    }


}
