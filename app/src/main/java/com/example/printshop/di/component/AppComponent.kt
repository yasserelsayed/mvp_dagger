package com.example.printshop.di.component

import android.content.Context
import com.example.printshop.di.module.AppModule
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent{
     fun provideAppContext(): Context
     fun provideFirebaseAuth(): FirebaseAuth
     fun provideFirebaseDatabase(): FirebaseFirestore
}