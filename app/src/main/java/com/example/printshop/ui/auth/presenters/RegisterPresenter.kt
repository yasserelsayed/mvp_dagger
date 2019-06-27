package com.example.printshop.ui.auth.presenters

import android.util.Log
import com.example.printshop.R
import com.example.printshop.domain.User
import com.example.printshop.ui.auth.Authmvp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
class RegisterPresenter(val registerModel:Authmvp.iRegisterModel):Authmvp.iRegisterPresenter{

    lateinit var _view :Authmvp.iRegisterView
    val lst_disposables = arrayListOf<Disposable>()
    override fun setView(view: Authmvp.iRegisterView) {
        _view = view
    }

    override fun submit() {

        if(_view.getEmail().isEmpty()) {
            _view.setEmailError(R.string.msg_valid_email_required)
             return
        }

        if(_view.getName().isEmpty()) {
            _view.setNameError(R.string.msg_this_field_required)
           return
        }

        if(_view.getPassword().isEmpty()) {
            _view.setPasswordError(R.string.msg_this_field_required)
             return
        }

        if(_view.getConfirmPassword().isEmpty()) {
            _view.setConfirmPasswordError(R.string.msg_this_field_required)
            return
        }

        if(_view.getConfirmPassword()!=_view.getPassword()){
            _view.setConfirmPasswordError(R.string.msg_confirm_password_donot_match)
             return
            }
        _view.ShowLoading()
        lst_disposables.add( registerModel
                .register(User(_view.getEmail(), _view.getName(), _view.getPassword(), _view.getPromoCode()))
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