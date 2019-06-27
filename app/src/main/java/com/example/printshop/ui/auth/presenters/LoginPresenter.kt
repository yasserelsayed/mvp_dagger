package com.example.printshop.ui.auth.presenters

import android.util.Log
import com.example.printshop.R
import com.example.printshop.domain.User
import com.example.printshop.ui.auth.Authmvp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
public class LoginPresenter(val loginModel:Authmvp.iLoginModel):Authmvp.iLoginPresenter{

    lateinit var _view :Authmvp.iLoginView
    val lst_disposables = arrayListOf<Disposable>()
    override fun setView(view: Authmvp.iLoginView) {
        _view = view
    }

    override fun OnSubmit() {

        if(_view.getEmail().isEmpty()) {
            _view.setEmailError(R.string.msg_valid_email_required)
             return
        }


        if(_view.getPassword().isEmpty()) {
            _view.setPasswordError(R.string.msg_this_field_required)
             return
        }

        _view.ShowLoading()
        lst_disposables.add( loginModel
                .login(User(_view.getEmail(),"", _view.getPassword(), ""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({user->
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