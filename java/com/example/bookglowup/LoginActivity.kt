package com.example.bookglowup

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bookglowup.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        navigationOfViews()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun navigationOfViews(){
        binding.btnLogin.setOnClickListener{
            loginValidation()
        }
    }
    private fun loginValidation() {
        val userEmail = binding.etLoginEmail.text.toString().trim()
        val userPassword = binding.etLoginPassword.text.toString().trim()
        if (userEmail.isEmpty() && userPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show()
        } else if (userEmail.isEmpty()) {
            Toast.makeText(this, "Please fill the email id", Toast.LENGTH_SHORT).show()
        } else if (userPassword.isEmpty()) {
            Toast.makeText(this, "Please fill the password", Toast.LENGTH_SHORT).show()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show()
        }else if (userPassword.length<8){
            Toast.makeText(this, "Please enter at least 8 characters",
                Toast.LENGTH_SHORT).show()
        } else {
            auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Registered Successfully",
                            Toast.LENGTH_SHORT).show()
                        UtilObject.screenNavigation(this@LoginActivity, HomeActivity::class.java)
                    } else {
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }
    }
}