package com.example.salespersonapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.ProcessBuilder.Redirect

class Sign_Up : AppCompatActivity() {
    lateinit var editFullName: TextView
    lateinit var editEmailAddress: EditText
    private lateinit var editPassword: EditText
    lateinit var editConfirmPassword: EditText
    lateinit var editUserId: EditText
    private lateinit var btnSignUp: Button
    lateinit var tvRedirectLogin: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        editFullName = findViewById(R.id.editFullName)
        editEmailAddress = findViewById(R.id.editEmailAddress)
        editPassword = findViewById(R.id.editPassword)
        editConfirmPassword = findViewById(R.id.editConfirmPassword)
        editUserId = findViewById(R.id.editUserId)
        btnSignUp = findViewById(R.id.btnSignUp)
        tvRedirectLogin = findViewById(R.id.tvRedirectLogin)

        auth = Firebase.auth

        btnSignUp.setOnClickListener {
            signUpUser()
        }
        tvRedirectLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    private fun signUpUser(){
        val FullName = editFullName.text.toString()
        val email = editEmailAddress.text.toString()
        val password = editPassword.text.toString()
        val confirmPassword = editConfirmPassword.text.toString()
        val userId = editUserId.text.toString()


        if (FullName.isBlank()||email.isBlank()||password.isBlank()||confirmPassword.isBlank()||userId.isBlank()){
            Toast.makeText(this,"Name,Email and Password cant be blank", Toast.LENGTH_SHORT).show()
            return
        }
        if (password != confirmPassword){
            Toast.makeText(this,"Passwords dont match", Toast.LENGTH_SHORT).show()
            return
        }
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this,"Successfully Signed in",Toast.LENGTH_SHORT).show()
                finish()
            }else
                Toast.makeText(this,"Sign Up failed!",Toast.LENGTH_SHORT).show()
        }
    }
}