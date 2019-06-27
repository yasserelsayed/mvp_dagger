package com.example.printshop.data.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserDataModel(var name: String? , var phone: String?, var points: Int?, var profilePic: String, var promocCode: String?)