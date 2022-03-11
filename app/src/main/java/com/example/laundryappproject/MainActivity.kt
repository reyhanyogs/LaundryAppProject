package com.example.laundryappproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.laundryappproject.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = Firebase.auth.currentUser

        if(user?.displayName!=null){
            binding.name.text = user.displayName
        } else {
            binding.name.text = "@string/login_gagal"
        }

        binding.btnLogout.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.btnLogout -> {
                Firebase.auth.signOut()
                startActivity(Intent(this, ActivityLogin::class.java))
                finish()
            }
        }
    }
}