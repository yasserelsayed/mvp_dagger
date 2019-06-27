package com.example.printshop.di.component

import com.example.printshop.di.module.AuthModule
import com.example.printshop.di.scope.ActivityScope
import com.example.printshop.ui.auth.views.ForgetpassFragment
import com.example.printshop.ui.auth.views.LoginFragment
import com.example.printshop.ui.auth.views.RegisterFragment
import dagger.Component

@ActivityScope
@Component(modules = [AuthModule::class],dependencies = [AppComponent::class])
interface AuthComponent {
    fun inject(register:RegisterFragment)
    fun inject(login:LoginFragment)
    fun inject(forgetPassFrag:ForgetpassFragment)
}