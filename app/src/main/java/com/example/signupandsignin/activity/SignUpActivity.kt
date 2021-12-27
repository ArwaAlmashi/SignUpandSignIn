package com.example.signupandsignin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.mynoteapp.database.DatabaseHelper
import com.example.mynoteapp.lightStatueBar
import com.example.mynoteapp.setFullScreen
import com.example.signupandsignin.databinding.ActivitySignUpBinding
import com.example.signupandsignin.model.User
import java.net.URL

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var user: User
    private lateinit var allUsers : ArrayList<User>

    private val databaseHelper by lazy { DatabaseHelper(applicationContext) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFullScreen(window)
        lightStatueBar(window)

        Glide.with(this)
            .load(URL("https://cdn.dribbble.com/users/2003490/screenshots/4894506/space_animation_-_small_file.gif"))
            .into(binding.backgroundImage)

        allUsers = databaseHelper.readAllUsers()


        binding.apply {

            signInButton.setOnClickListener {
                val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                startActivity(intent)
            }

            signUpButton.setOnClickListener {
                if (name.text == null || email.text == null || password.text == null || phoneNumber.text == null) {
                    Toast.makeText(this@SignUpActivity, "Fill all fields", Toast.LENGTH_SHORT).show()
                } else {
                    val userName = name.text.toString()
                    val userEmail = email.text.toString()
                    val userPhoneNumber = phoneNumber.text.toString()
                    val userPassword = password.text.toString()
                    val newUser = User(userName, userEmail, userPhoneNumber, userPassword)

                    user = databaseHelper.getUser(userEmail)
                    if (user.name =="") {
                        databaseHelper.saveNotes(newUser)
                        val intent = Intent(this@SignUpActivity, DetailsActivity::class.java)
                        intent.putExtra("user",newUser)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@SignUpActivity, "User is exist, please sign in", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }


    }
}