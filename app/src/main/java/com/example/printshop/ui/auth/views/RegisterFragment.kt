package com.example.printshop.ui.auth.views

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import com.example.printshop.R
import com.example.printshop.ui.auth.Authmvp
import com.example.printshop.ui.base.AppFragment
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import kotlinx.android.synthetic.main.fragment_register.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class RegisterFragment : AppFragment(),View.OnFocusChangeListener,View.OnClickListener,Authmvp.iRegisterView {



    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    val RC_SIGN_IN:Int = 1112

    @set:Inject
    var _authWithSocialPresenter:Authmvp.iAuthWithSocialPresenter? =null
    @set:Inject
    var _registerPresenter:Authmvp.iRegisterPresenter? =null
    @set:Inject
    var _googleSignInClient:GoogleSignInClient? =null
    @set:Inject
    var _CallbackManager: CallbackManager? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         _authActivity?._authComponent?.inject(this)
         return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onStart() {
        super.onStart()
        edt_name.onFocusChangeListener = this
        edt_name.tag = ObjectAnimator.ofFloat(edt_name, "scaleX", 1.2f).apply {duration = 500}
        edt_promo.onFocusChangeListener = this
        edt_promo.tag = ObjectAnimator.ofFloat(edt_promo, "scaleX", 1.2f).apply {duration = 500}
        edt_email.onFocusChangeListener = this
        edt_email.tag = ObjectAnimator.ofFloat(edt_email, "scaleX", 1.2f).apply {duration = 500}
        edt_password.onFocusChangeListener = this
        edt_password.tag = ObjectAnimator.ofFloat(edt_password, "scaleX", 1.2f).apply {duration = 500}
        edt_confirm_password.onFocusChangeListener = this
        edt_confirm_password.tag = ObjectAnimator.ofFloat(edt_confirm_password, "scaleX", 1.2f).apply {duration = 500}

        _registerPresenter?.setView(this)
        _authWithSocialPresenter?.setView(this)

        btn_submit.setOnClickListener(this)
        txt_login.setOnClickListener(this)
        img_facebook.setOnClickListener(this)
        img_gmailauth.setOnClickListener(this)

        btn_with_facebook.setReadPermissions("email", "public_profile")
        btn_with_facebook.registerCallback(_CallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                _authWithSocialPresenter?.OnSubmitFacebookToken(loginResult.accessToken)
            }
            override fun onCancel() {
            }
            override fun onError(error: FacebookException) {
            }
        })

        edt_email.requestFocus()
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        _registerPresenter?.unsubscribe()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

                try {
                    val account = task.getResult(ApiException::class.java)
                    _authWithSocialPresenter?.OnSubmitGoogleSignIn(account!!)
                } catch (e: ApiException) {
                 ShowNotification("Google sign in failed")
                }
        }else {
            _CallbackManager?.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onClick(v: View?) {
        when(v){
            btn_submit->_registerPresenter?.submit()
            txt_login->_authActivity?.TransitionToFragment(LoginFragment())
            img_gmailauth-> startActivityForResult(_googleSignInClient?.signInIntent,RC_SIGN_IN)
            img_facebook-> btn_with_facebook.performClick()
        }
    }


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

    override fun getEmail(): String {
       return edt_email.text.toString()
    }

    override fun getName(): String {
        return edt_name.text.toString()
    }

    override fun getPassword(): String {
       return edt_password.text.toString()
    }

    override fun getConfirmPassword(): String {
        return edt_confirm_password.text.toString()
    }



    override fun getPromoCode(): String {
        return edt_promo.text.toString()
    }

    override fun setEmailError(Error: Int) {
        if(Error >-1) {
            edt_email.error = getString(Error)
            edt_email.requestFocus()
        }else edt_email.error =null
    }

    override fun setConfirmPasswordError(Error: Int) {
        if(Error >-1) {
            edt_confirm_password.error = getString(Error)
            edt_confirm_password.requestFocus()
        }else edt_confirm_password.error =null
    }

    override fun setNameError(Error: Int) {
        if(Error >-1) {
            edt_name.error = getString(Error)
            edt_name.requestFocus()
        }else edt_name.error =null
    }

    override fun setPasswordError(Error: Int) {
        if(Error >-1) {
            edt_password.error = getString(Error)
            edt_password.requestFocus()
        }else edt_password.error =null
    }
}
