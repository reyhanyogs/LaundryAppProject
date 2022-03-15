package com.example.laundryappproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.laundryappproject.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class ActivityRegister : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.btnDaftar.setOnClickListener(this)
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload()
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.btnDaftar -> {
                if(binding.editTextNamaLengkap.text.isNotEmpty() && binding.editTextEmailRegister.text.isNotEmpty() && binding.editTextPasswordRegister.text.isNotEmpty()) {
                    register(binding.editTextNamaLengkap.text.toString(), binding.editTextEmailRegister.text.toString(), binding.editTextPasswordRegister.text.toString())
                } else {
                    Toast.makeText(this, "Silahkan mengisi semua form di atas", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun register(name: String, email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val user = Firebase.auth.currentUser
                if (user!=null){
                    val profileUpdate = userProfileChangeRequest {
                        displayName = name
                    }
                    user.updateProfile(profileUpdate)
                        .addOnCompleteListener{tasks ->
                            reload()
                        }
                } else {
                    Toast.makeText(this, "Register Gagal", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun reload(){
        startActivity(Intent(this, MainActivity::class.java))
    }
}