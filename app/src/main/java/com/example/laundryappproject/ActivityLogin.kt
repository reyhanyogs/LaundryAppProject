package com.example.laundryappproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.laundryappproject.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ActivityLogin : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.btnDaftarLogin.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload()
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnDaftarLogin -> {
                val registerIntent = Intent(this, ActivityRegister::class.java)
                startActivity(registerIntent)
            }
            R.id.btnLogin -> {
                if (binding.editTextEmail.text.isNotEmpty() && binding.editTextPassword.text.isNotEmpty()){
                    login(binding.editTextEmail.text.toString(), binding.editTextPassword.text.toString())
                } else {
                    Toast.makeText(this, "Silahkan mengisi email dan password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun login(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{task ->
            if (task.isSuccessful && task.result!=null) {
                if (task.result.user!=null) {
                    reload()
                } else {
                    Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun reload(){
        startActivity(Intent(this, MainActivity::class.java))
    }
}