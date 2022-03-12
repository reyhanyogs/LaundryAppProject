package com.example.laundryappproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.laundryappproject.databinding.ActivityWelcomeBinding

class ActivityWelcome : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewPagerAdapter = ViewPagerAdapter(this)
        binding.slideViewPager.adapter = viewPagerAdapter
        binding.dots.setViewPager(binding.slideViewPager)
        binding.btnMulai.setOnClickListener {
            val intent = Intent(this@ActivityWelcome, ActivityLogin::class.java)
            startActivity(intent)
        }
    }
}