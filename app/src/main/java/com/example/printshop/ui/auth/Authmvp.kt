package com.example.printshop.ui.auth

import com.example.printshop.domain.User
import com.example.printshop.ui.base.VIEW
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.Observable

interface Authmvp {

    interface iRegisterView: VIEW {
        fun getEmail():String
        fun getName():String
        fun getPassword():String
        fun getConfirmPassword():String
        fun getPromoCode():String
        fun setEmailError(Error:Int)
        fun setConfirmPasswordError(Error:Int)
        fun setNameError(Error:Int)
        fun setPasswordError(Error:Int)
    }
    interface iRegisterPresenter{
        fun setView(view:iRegisterView)
        fun submit()
        fun unsubscribe()
    }
    interface iRegisterModel{
        fun register(user: User): Observable<User>
    }


    interface iLoginView: VIEW {
        fun getEmail():String
        fun getPassword():String
        fun setEmailError(Error:Int)
        fun setPasswordError(Error:Int)
    }
    interface iLoginPresenter{
        fun setView(view:iLoginView)
        fun OnSubmit()
        fun unsubscribe()
    }
    interface iLoginModel{
        fun login(user: User): Observable<User>
    }


    interface iForgetPasswordView: VIEW {
        fun getEmail():String
        fun setEmailError(Error:Int)
    }

    interface iForgetPasswordPresenter{
        fun setView(view:iForgetPasswordView)
        fun OnSubmit()
        fun unsubscribe()
    }
    interface iForgetPasswordModel{
        fun ResetPassword(Email: String): Observable<Boolean>
    }



    interface iAuthWithSocialPresenter{
        fun setView(view:VIEW)
        fun OnSubmitGoogleSignIn(account:GoogleSignInAccount)
        fun OnSubmitFacebookToken(token:AccessToken)
        fun unsubscribe()
    }
    interface iAuthWithSocialModel{
        fun AuthWithGoogle(acct: GoogleSignInAccount): Observable<User>
        fun AuthWithFacebook(token:AccessToken): Observable<User>
    }

}