package com.example.printshop.ui.auth.models

import com.example.printshop.data.AuthRepositories
import com.example.printshop.domain.User
import com.example.printshop.ui.auth.Authmvp
import io.reactivex.Observable

class RegisterModel(val authNetwork:AuthRepositories.iAuthNetwork) :Authmvp.iRegisterModel{

    override fun register(user: User): Observable<User> {
                return authNetwork.register(user)
    }
}