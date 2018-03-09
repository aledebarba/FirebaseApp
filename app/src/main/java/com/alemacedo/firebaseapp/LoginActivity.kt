package com.alemacedo.firebaseapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast
import com.alemacedo.firebaseapp.extensions.getText

import com.google.firebase.auth.FirebaseUser

import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password




class LoginActivity : AppCompatActivity() {

    private lateinit var  mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        btCriar.setOnClickListener {
            Toast.makeText(this, inputEmail.getText(),
                    Toast.LENGTH_SHORT).show()
            mAuth.createUserWithEmailAndPassword(inputEmail.getText(), inputSenha.getText())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = mAuth.currentUser
                            Toast.makeText(this, "Criou com sucesso.",
                                    Toast.LENGTH_SHORT).show()
                        } else {

                            Toast.makeText(this, task.exception?.message,
                                    Toast.LENGTH_SHORT).show()
                            //updateUI(null)
                        }

                        // ...
                    }
        }

        btLogin.setOnClickListener {
            mAuth.signInWithEmailAndPassword(inputEmail.getText(), inputSenha.getText())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = mAuth.currentUser
                            Toast.makeText(this, "Logou com sucesso.",
                                    Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                            //updateUI(null)
                        }

                        // ...
                    }
        }

        btLogout.setOnClickListener {
                mAuth.signOut()
        }

        btSendMail.setOnClickListener {
            val user = mAuth.currentUser
            user?.sendEmailVerification()
                    ?.addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful){
                            Toast.makeText(this, "Sucesso!.",
                                    Toast.LENGTH_SHORT).show()
                    }else{
                            Toast.makeText(this, "Falhou.",
                                    Toast.LENGTH_SHORT).show()
                    }
            })
        }

    }


}
