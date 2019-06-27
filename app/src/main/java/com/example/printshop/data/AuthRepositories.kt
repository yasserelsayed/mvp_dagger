package com.example.printshop.data

import com.example.printshop.domain.User
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.Observable

interface AuthRepositories{
    interface iAuthNetwork{
          fun register(user: User): Observable<User>
          fun login(user: User): Observable<User>
          fun firebaseAuthWithGoogle(acct: GoogleSignInAccount): Observable<User>
          fun authFacebookAccess(token: AccessToken): Observable<User>
          fun forgetPassword(Email: String): Observable<Boolean>
    }
}