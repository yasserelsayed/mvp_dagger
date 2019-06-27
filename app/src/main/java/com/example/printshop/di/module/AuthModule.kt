package com.example.printshop.di.module

import com.example.printshop.R
import com.example.printshop.data.AuthRepositories
import com.example.printshop.data.network.AuthRepositry
import com.example.printshop.di.scope.ActivityScope
import com.example.printshop.ui.auth.Authmvp
import com.example.printshop.ui.auth.models.AuthWithSocialModel
import com.example.printshop.ui.auth.models.ForgetPassModel
import com.example.printshop.ui.auth.models.LoginModel
import com.example.printshop.ui.auth.models.RegisterModel
import com.example.printshop.ui.auth.presenters.AuthWithSocialPresenter
import com.example.printshop.ui.auth.presenters.ForgetPassPresenter
import com.example.printshop.ui.auth.presenters.LoginPresenter
import com.example.printshop.ui.auth.presenters.RegisterPresenter
import com.example.printshop.ui.base.AuthActivity
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AuthModule (val activity: AuthActivity){

    @Provides
    @ActivityScope
    fun provideAuthRepositry(firebaseAuth:FirebaseAuth): AuthRepositories.iAuthNetwork {
        return  AuthRepositry(firebaseAuth,activity)
    }

    @Provides
    @ActivityScope
    fun provideRegisterModel(authNetwork:AuthRepositories.iAuthNetwork): Authmvp.iRegisterModel {
        return  RegisterModel(authNetwork)
    }

    @Provides
    @ActivityScope
    fun provideRegisterPresenter(registerModel:Authmvp.iRegisterModel): Authmvp.iRegisterPresenter {
        return  RegisterPresenter(registerModel)
    }



    @Provides
    @ActivityScope
    fun provideLoginrModel(authNetwork:AuthRepositories.iAuthNetwork): Authmvp.iLoginModel {
        return  LoginModel(authNetwork)
    }

    @Provides
    @ActivityScope
    fun provideLoginPresenter(loginModel:Authmvp.iLoginModel): Authmvp.iLoginPresenter {
        return  LoginPresenter(loginModel)
    }


    @Provides
    @ActivityScope
    fun provideForgetPasswordModel(authNetwork:AuthRepositories.iAuthNetwork): Authmvp.iForgetPasswordModel {
        return  ForgetPassModel(authNetwork)
    }

    @Provides
    @ActivityScope
    fun provideForgetPassPresenter(forgetPasswordModel:Authmvp.iForgetPasswordModel): Authmvp.iForgetPasswordPresenter {
        return  ForgetPassPresenter(forgetPasswordModel)
    }


    @Provides
    @ActivityScope
    fun provideAuthWithSocialModel(authNetwork:AuthRepositories.iAuthNetwork): Authmvp.iAuthWithSocialModel {
        return  AuthWithSocialModel(authNetwork)
    }

    @Provides
    @ActivityScope
    fun provideAuthWithSocialPresenter(authWithSocialModel:Authmvp.iAuthWithSocialModel): Authmvp.iAuthWithSocialPresenter {
        return  AuthWithSocialPresenter(authWithSocialModel)
    }


    @Provides
    @ActivityScope
    fun provideGoogleSignInOptions(): GoogleSignInOptions {
          return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }


    @Provides
    @ActivityScope
    fun provideGoogleSignInClient(gso: GoogleSignInOptions): GoogleSignInClient {
        return GoogleSignIn.getClient(activity, gso)
    }


    @Provides
    @ActivityScope
    fun provideCallbackManager(): CallbackManager {
        return CallbackManager.Factory.create()
    }

}