package com.example.figmadeneme

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.app.ActivityManager
import com.example.figmadeneme.databinding.ActivityMainBinding // Import ettiğinizden emin olun

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var loginButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         binding.textViewSignIn.setOnClickListener {
            val intent = Intent(applicationContext, BilgiEkrani::class.java)
            startActivity(intent)
            binding.textViewSignIn.setOnClickListener(View.OnClickListener {
                if (binding.usernameText.text.toString() == "yazilim" && binding.passwordText.text.toString() == "muhendisligi") {
                    Toast.makeText(this, "Login Succesful", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@MainActivity, BilgiEkrani::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}



