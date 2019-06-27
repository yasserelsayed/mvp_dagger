package com.example.printshop.ui.auth.views


import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.printshop.R
import com.example.printshop.ui.auth.Authmvp
import com.example.printshop.ui.base.AppFragment
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.btn_submit
import kotlinx.android.synthetic.main.fragment_login.edt_email
import kotlinx.android.synthetic.main.fragment_login.edt_password
import javax.inject.Inject

 class LoginFragment : AppFragment(),Authmvp.iLoginView,View.OnClickListener,View.OnFocusChangeListener {

     override fun onFocusChange(v: View?, hasFocus: Boolean) {
         val animator:ObjectAnimator =  v?.tag as ObjectAnimator
         if(hasFocus) {
             animator.start()
             context?.let {
                 (v as EditText).setHintTextColor(ContextCompat.getColor(it, android.R.color.black))
             }
         }else {
             animator.reverse()
             context?.let {
                 (v as EditText).setHintTextColor(ContextCompat.getColor(it, android.R.color.white))
             }
         }
     }

     override fun onClick(v: View?) {
        when(v) {
            btn_submit -> _loginPresenter?.OnSubmit()
            txt_signup -> _authActivity?.TransitionToFragment(RegisterFragment())
            txt_forgetpass->_authActivity?.TransitionToFragment(ForgetpassFragment())
        }
    }


    override fun getPassword(): String {
       return edt_password.text.toString()
    }

    override fun setEmailError(Error: Int) {
        if(Error>-1) {
            edt_email.error = getString(Error)
            edt_email.requestFocus()
        }else
            edt_email.error = null
    }

    override fun setPasswordError(Error: Int) {
        if(Error>-1) {
            edt_password.error = getString(Error)
            edt_password.requestFocus()
        }else
            edt_password.error = null
    }

    override fun getEmail(): String {
       return edt_email.text.toString()
    }

    @set:Inject
    var _loginPresenter: Authmvp.iLoginPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _authActivity?._authComponent?.inject(this)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

     override fun onStart() {
         super.onStart()
         edt_email.onFocusChangeListener = this
         edt_email.tag = ObjectAnimator.ofFloat(edt_email, "scaleX", 1.2f).apply {duration = 500}
         edt_password.onFocusChangeListener = this
         edt_password.tag = ObjectAnimator.ofFloat(edt_password, "scaleX", 1.2f).apply {duration = 500}
         btn_submit.setOnClickListener(this)
         txt_signup.setOnClickListener(this)
         txt_forgetpass.setOnClickListener(this)


         edt_email.requestFocus()
     }

     override fun onResume() {
        super.onResume()
        _loginPresenter?.setView(this)
    }
}
