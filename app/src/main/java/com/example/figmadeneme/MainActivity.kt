package com.example.figmadeneme

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.figmadeneme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    private val PREFS_NAME = "MyPrefs"
    private val KEY_REMEMBER_ME = "remember_me"
    private val KEY_USERNAME = "username"
    private val KEY_PASSWORD = "password"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Daha önce hatırlanan kullanıcı adı ve şifre varsa, bu bilgileri doldur
        val rememberedUsername = sharedPreferences.getString(KEY_USERNAME, null)
        val rememberedPassword = sharedPreferences.getString(KEY_PASSWORD, null)
        val rememberMe = sharedPreferences.getBoolean(KEY_REMEMBER_ME, false)

        if (rememberMe) {
            binding.usernameText.setText(rememberedUsername)
            binding.passwordText.setText(rememberedPassword)
            binding.saveLoginCheckBox.isChecked = true
        } else {
            binding.saveLoginCheckBox.isChecked = false
        }

        binding.textViewSignIn.setOnClickListener {
            val username = binding.usernameText.text.toString()
            val password = binding.passwordText.text.toString()
            val rememberMe = binding.saveLoginCheckBox.isChecked

            if (authenticate(username, password)) {
                val editor = sharedPreferences.edit()
                if (rememberMe) {
                    editor.putString(KEY_USERNAME, username)
                    editor.putString(KEY_PASSWORD, password)
                    editor.putBoolean(KEY_REMEMBER_ME, true)
                } else {
                    editor.remove(KEY_USERNAME)
                    editor.remove(KEY_PASSWORD)
                    editor.putBoolean(KEY_REMEMBER_ME, false)
                }
                editor.apply()

                Toast.makeText(this, "Giriş Başarılı", Toast.LENGTH_SHORT).show()
                navigateToBilgiEkrani()
            } else {
                Toast.makeText(this, "Giriş Başarısız", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun authenticate(username: String, password: String): Boolean {
        // Gerçek kimlik doğrulama işlemini burada yapmalısınız
        return username == "yazilim" && password == "muhendisligi"
    }

    private fun navigateToBilgiEkrani() {
        val intent = Intent(this, BilgiEkrani::class.java)
        startActivity(intent)
        finish() // Giriş ekranını kapatabiliriz
    }
}