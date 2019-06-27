package com.example.printshop.ui.auth.models

import com.example.printshop.data.AuthRepositories
import com.example.printshop.domain.User
import com.example.printshop.ui.auth.Authmvp
import io.reactivex.Observable

class LoginModel(val authNetwork:AuthRepositories.iAuthNetwork) :Authmvp.iLoginModel{

    override fun login(user: User): Observable<User> {
                return authNetwork.login(user)
    }
}