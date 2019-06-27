package com.example.printshop.ui.auth.views

import android.animation.ObjectAnimator
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.printshop.R
import com.example.printshop.ui.auth.Authmvp
import com.example.printshop.ui.base.AppFragment
import kotlinx.android.synthetic.main.fragment_login.btn_submit
import kotlinx.android.synthetic.main.fragment_login.edt_email
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ForgetpassFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ForgetpassFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ForgetpassFragment : AppFragment(),View.OnClickListener,View.OnFocusChangeListener,Authmvp.iForgetPasswordView {

    override fun onClick(v: View?) {
      _forgetPasswordPresenter?.OnSubmit()
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

    override fun onResume() {
        super.onResume()
        _forgetPasswordPresenter?.setView(this)
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    @set:Inject
    var _forgetPasswordPresenter: Authmvp.iForgetPasswordPresenter? = null
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
        return inflater.inflate(R.layout.fragment_forgotpass, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onStart() {
        super.onStart()
        edt_email.onFocusChangeListener = this
        edt_email.tag = ObjectAnimator.ofFloat(edt_email, "scaleX", 1.2f).apply {duration = 500}

        btn_submit.setOnClickListener(this)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ForgetpassFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ForgetpassFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun getEmail(): String {
        return edt_email.text.toString()
    }


    override fun setEmailError(Error: Int) {
        if(Error>-1) {
            edt_email.error = getString(Error)
            edt_email.requestFocus()
        }else
            edt_email.error = null
    }
}
