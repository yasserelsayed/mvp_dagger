package com.example.printshop.ui.auth.presenters


import com.example.printshop.domain.User
import com.example.printshop.ui.auth.Authmvp
import com.example.printshop.ui.base.VIEW
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AuthWithSocialPresenter(val authWithSocialModel:Authmvp.iAuthWithSocialModel):Authmvp.iAuthWithSocialPresenter{

    var _view: VIEW? = null
    override fun setView(view: VIEW) {
        _view = view
    }


    val lst_disposables = arrayListOf<Disposable>()

    override fun OnSubmitGoogleSignIn(account: GoogleSignInAccount) {
        lst_disposables.add( authWithSocialModel
            .AuthWithGoogle(account)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _view?.ShowNotification("done")
            },{
                _view?.ShowNotification("error")
            }))
    }

    override fun OnSubmitFacebookToken(token: AccessToken) {
        lst_disposables.add( authWithSocialModel
            .AuthWithFacebook(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _view?.ShowNotification("done")
            },{
                _view?.ShowNotification("error")
            }))
    }

    override fun unsubscribe() {
        for(item:Disposable in lst_disposables){
            if(!item.isDisposed)
                item.dispose()
        }
    }


}