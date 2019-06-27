package com.example.printshop.di.module

import android.app.Application
import android.content.Context
import com.example.printshop.di.scope.ActivityScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (val application: Application,val firebaseAuth: FirebaseAuth,val firebaseFirestore: FirebaseFirestore){

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return firebaseAuth
    }

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseFirestore {
        return firebaseFirestore
    }

    @Provides
    @Singleton
    fun provideAppContext(): Context {
        return application
    }
}