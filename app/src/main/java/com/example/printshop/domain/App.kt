package com.example.printshop.domain

import android.app.Application
import com.example.printshop.di.component.AppComponent
import com.example.printshop.di.component.DaggerAppComponent
import com.example.printshop.di.module.AppModule
import com.facebook.FacebookSdk
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class App :Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this,FirebaseAuth.getInstance(),
            FirebaseFirestore.getInstance())).build()
    }
}