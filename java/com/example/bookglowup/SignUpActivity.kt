package com.example.bookglowup

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bookglowup.databinding.ActivitySignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        navigationOfViews()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun navigationOfViews() {
        binding.btnSignUp.setOnClickListener {
            signUpValidation()
        }
    }
    private fun signUpValidation() {
        val userName = binding.etNameSignUp.text.toString().trim()
        val userEmail = binding.etEmailSignUp.text.toString().trim()
        val userPassword = binding.etPasswordSignUp.text.toString().trim()
        if (userName.isEmpty() && userEmail.isEmpty() && userPassword.isEmpty()) {
            Toast.makeText(this, "please fill all the details", Toast.LENGTH_SHORT).show()
        } else if (userName.isEmpty()) {
            Toast.makeText(this, "please enter the name", Toast.LENGTH_SHORT).show()
        } else if (userEmail.isEmpty()) {
            Toast.makeText(this, "please enter the email", Toast.LENGTH_SHORT).show()
        } else if (userPassword.isEmpty()) {
            Toast.makeText(this, "please enter the password", Toast.LENGTH_SHORT).show()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            Toast.makeText(this, "please enter valid email address",
                Toast.LENGTH_SHORT).show()
        } else if (userPassword.length < 8) {
            Toast.makeText(this, "enter at least 8 characters", Toast.LENGTH_SHORT).show()
        } else {
            auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Successfully signed Up",
                            Toast.LENGTH_SHORT).show()
                        UtilObject.screenNavigation(this@SignUpActivity, HomeActivity::class.java)
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