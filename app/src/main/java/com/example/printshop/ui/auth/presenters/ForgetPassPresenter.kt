package com.example.printshop.ui.auth.presenters

import android.util.Log
import com.example.printshop.R
import com.example.printshop.domain.User
import com.example.printshop.ui.auth.Authmvp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
public class ForgetPassPresenter(val forgetPassword:Authmvp.iForgetPasswordModel):Authmvp.iForgetPasswordPresenter{

    lateinit var _view :Authmvp.iForgetPasswordView
    val lst_disposables = arrayListOf<Disposable>()
    override fun setView(view: Authmvp.iForgetPasswordView) {
        _view = view
    }

    override fun OnSubmit() {

        if(_view.getEmail().isEmpty()) {
            _view.setEmailError(R.string.msg_valid_email_required)
             return
        }


        _view.ShowLoading()
        lst_disposables.add(forgetPassword
                .ResetPassword(_view.getEmail())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _view.HideLoading()
                }, {error->
                    _view.HideLoading()
                    error.message?.let {
                        _view.ShowNotification(it)
                    }
                }))

    }

    override fun unsubscribe() {
        for(item:Disposable in lst_disposables){
            if(!item.isDisposed)
                item.dispose()
        }
    }


}