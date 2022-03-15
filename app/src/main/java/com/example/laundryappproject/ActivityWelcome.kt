package com.example.laundryappproject

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.laundryappproject.databinding.ActivityWelcomeBinding

class ActivityWelcome : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewPagerAdapter = ViewPagerAdapter(this)
        binding.slideViewPager.adapter = viewPagerAdapter
        binding.dots.setViewPager(binding.slideViewPager)
        preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
        val firstTime = preferences.getString("FirstTimeInstall", "")

        if(firstTime.equals("Yes")) {
            val intent = Intent(this@ActivityWelcome, ActivityLogin::class.java)
            startActivity(intent)
            finish()
        }

        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString("FirstTimeInstall", "Yes")
        editor.apply()

        binding.btnMulai.setOnClickListener {
            val intent = Intent(this@ActivityWelcome, ActivityLogin::class.java)
            startActivity(intent)
        }
    }
}