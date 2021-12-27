package com.example.signupandsignin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.mynoteapp.database.DatabaseHelper
import com.example.mynoteapp.lightStatueBar
import com.example.mynoteapp.setFullScreen
import com.example.signupandsignin.databinding.ActivitySignInBinding
import com.example.signupandsignin.model.User
import java.net.URL

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    private lateinit var user: User
    private lateinit var allUsers: ArrayList<User>

    private val databaseHelper by lazy { DatabaseHelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFullScreen(window)
        lightStatueBar(window)

        allUsers = databaseHelper.readAllUsers()

        Glide.with(this)
            .load(URL("https://cdn.dribbble.com/users/2003490/screenshots/4894506/space_animation_-_small_file.gif"))
            .into(binding.backgroundImage)

        binding.apply {

            signUpButton.setOnClickListener {
                val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
                startActivity(intent)
            }

            signInButton.setOnClickListener {
                if (email.text == null || password.text == null) {
                    Toast.makeText(this@SignInActivity, "Fill all fields", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val userEmail = email.text.toString()
                    val userPassword = password.text.toString()

                    user = databaseHelper.getUser(userEmail)

                    if (user.name == "") {
                        Toast.makeText(
                            this@SignInActivity,
                            "User is not exist, please sign up",
                            Toast.LENGTH_SHORT
                        ).show()


                    } else {
                        if (userPassword == user.password) {
                            val intent = Intent(this@SignInActivity, DetailsActivity::class.java)
                            intent.putExtra("user", user)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@SignInActivity, "Wrong password", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }


        }


    }
}