package com.example.salespersonapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var editEmailAddress: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnLogin:Button
    lateinit var tvRedirectSignUp: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvRedirectSignUp = findViewById(R.id.tvRedirectSignUp)
        btnLogin = findViewById(R.id.btnLogin)
        editEmailAddress = findViewById(R.id.editEmailAddress)
        editPassword = findViewById(R.id.editPassword)

        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener{
            login()
        }
        tvRedirectSignUp.setOnClickListener {
            val intent = Intent(this,Sign_Up::class.java)
            startActivity(intent)
            finish()
        }

    }
    private fun login(){
        val email = editEmailAddress.text.toString()
        val password = editPassword.text.toString()

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this, "Successfully loggedIn", Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(this, "Log in failed", Toast.LENGTH_SHORT).show()
        }
    }
}