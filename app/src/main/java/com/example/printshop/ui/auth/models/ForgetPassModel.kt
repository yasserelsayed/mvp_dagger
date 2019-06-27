package com.example.printshop.ui.auth.models

import com.example.printshop.data.AuthRepositories
import com.example.printshop.domain.User
import com.example.printshop.ui.auth.Authmvp
import io.reactivex.Observable

class ForgetPassModel(private val authNetwork:AuthRepositories.iAuthNetwork) :Authmvp.iForgetPasswordModel{
    override fun ResetPassword(Email: String): Observable<Boolean> {
        return authNetwork.forgetPassword(Email)
    }
}