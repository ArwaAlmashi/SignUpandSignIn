package com.example.signupandsignin.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.mynoteapp.lightStatueBar
import com.example.mynoteapp.setFullScreen
import com.example.signupandsignin.databinding.ActivityDetailsBinding
import com.example.signupandsignin.databinding.ActivitySignInBinding
import com.example.signupandsignin.model.User
import java.net.URL

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFullScreen(window)
        lightStatueBar(window)

        Glide.with(this)
            .load(URL("https://cdn.dribbble.com/users/2003490/screenshots/4894506/space_animation_-_small_file.gif"))
            .into(binding.backgroundImg)

        val user = intent.getSerializableExtra("user") as User

        binding.apply {
            name.text = "Name: ${user.name}"
            email.text = "Email: ${user.email}"
            phone.text = "Phone: ${user.phone}"
            password.text = "Password: ${user.password}"
            welcome.text = "Welcome ${user.name}"

            signOutButton.setOnClickListener {
                val intent = Intent( this@DetailsActivity, SignInActivity::class.java)
                startActivity(intent)
            }
        }


    }
}