package com.example.signupandsignin.model

import java.io.Serializable

data class User(val name: String, val email: String, val phone: String, val password: String) : Serializable