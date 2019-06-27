package com.example.printshop.data.network

import com.example.printshop.data.AuthRepositories
import com.example.printshop.domain.User
import com.example.printshop.ui.base.AppActivity
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.*
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe


class AuthRepositry(val firebaseAuth: FirebaseAuth, val activity:AppActivity) : AuthRepositories.iAuthNetwork {

    override fun authFacebookAccess(token: AccessToken): Observable<User> {
        return Observable.create(ObservableOnSubscribe<User> { obs ->
            val credential = FacebookAuthProvider.getCredential(token.token)
            firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity) { authTaks ->
                    if (authTaks.isSuccessful) {
                        val currentUser = firebaseAuth.currentUser
                        obs.onNext(
                            User(
                                currentUser?.email.toString()
                                , currentUser?.displayName.toString()
                                , ""
                                , ""
                            )
                        )
                        obs.onComplete()
                    } else {
                        obs.onError(object : Throwable() {
                            override val message: String?
                                get() = authTaks.exception?.message
                        })
                    }
                }
     })
    }

    override fun firebaseAuthWithGoogle(acct: GoogleSignInAccount): Observable<User> {
        return Observable.create(ObservableOnSubscribe<User> { obs ->
            val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
            firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity) {authTaks->
                    if(authTaks.isSuccessful) {
                        val currentUser = firebaseAuth.currentUser
                        obs.onNext(User(currentUser?.email.toString()
                                        ,currentUser?.displayName.toString()
                                        ,""
                                        ,""))
                        obs.onComplete()
                    }else{
                        obs.onError(object :Throwable(){
                            override val message: String?
                                get() = authTaks.exception?.message
                        })
                    }
                }
        })
    }


    override fun forgetPassword(Email: String): Observable<Boolean> {
        return Observable.create(ObservableOnSubscribe<Boolean> { obs ->
            firebaseAuth.sendPasswordResetEmail(Email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) obs.onNext(true)
                    else{
                         obs.onError(object :Throwable(){
                             override val message: String?
                            get() = task.exception?.message
                          })
                }
                    obs.onComplete()
                }
        })
    }

    override fun login(user: User): Observable<User> {
        return Observable.create(ObservableOnSubscribe<User> {obs->
            firebaseAuth.signInWithEmailAndPassword(user.email,user.password)
                .addOnCompleteListener(activity) {authTaks->
                    if(authTaks.isSuccessful) {
                        obs.onNext(user)
                        obs.onComplete()
                    }else{
                        obs.onError(object :Throwable(){
                            override val message: String?
                                get() = authTaks.exception?.message
                        })
                    }
                }
        })
    }

    override fun register(user: User): Observable<User> {
       val profileUpdates = UserProfileChangeRequest.Builder()
       return Observable.create(ObservableOnSubscribe<User> {obs->
               firebaseAuth.createUserWithEmailAndPassword(user.email,user.password)
               .addOnCompleteListener(activity) {authTaks->
                   if(authTaks.isSuccessful) {
                      val firebaseUser:FirebaseUser? = firebaseAuth.currentUser
                          firebaseUser?.updateProfile(profileUpdates.setDisplayName(user.name).build())
                           ?.addOnCompleteListener {profileTask->
                               if(profileTask.isSuccessful) {
                                   obs.onNext(user)
                                   obs.onComplete()
                               }else{
                                   obs.onError(object :Throwable(){
                                       override val message: String?
                                           get() = profileTask.exception?.message
                                   })
                               }
                           }

                   }else{
                       obs.onError(object :Throwable(){
                           override val message: String?
                               get() = authTaks.exception?.message
                       })
                   }
               }
        })


    }
}

//val firebaseDatabase: FirebaseFirestore,
//        val usr = UserDataModel("ad2","000000",0,"","")
////        val usr = HashMap<String, Any>()
////        usr["name"] = "Ada"
////        usr["phone"] = "7868787"
////        usr["points"] = 0
//
//        firebaseDatabase.collection("users")
//            .add(usr)
//            .addOnSuccessListener { documentReference ->
//               val i:Int = 9+6
//            }.addOnFailureListener { e ->
//                val i:Int = 9+6
//            }