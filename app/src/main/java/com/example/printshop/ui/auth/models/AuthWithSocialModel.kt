package com.example.printshop.ui.auth.models

import com.example.printshop.data.AuthRepositories
import com.example.printshop.domain.User
import com.example.printshop.ui.auth.Authmvp
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.Observable

class AuthWithSocialModel(private val authNetwork:AuthRepositories.iAuthNetwork) :Authmvp.iAuthWithSocialModel{

    override fun AuthWithGoogle(acct: GoogleSignInAccount): Observable<User> {
       return authNetwork.firebaseAuthWithGoogle(acct)
    }

    override fun AuthWithFacebook(token: AccessToken): Observable<User> {
        return authNetwork.authFacebookAccess(token)
    }

}