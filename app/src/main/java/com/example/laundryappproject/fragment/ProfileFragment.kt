package com.example.laundryappproject.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laundryappproject.ActivityLogin
import com.example.laundryappproject.ListAdapter
import com.example.laundryappproject.R
import com.example.laundryappproject.databinding.FragmentProfileBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvList.setHasFixedSize(true)
        binding.rvList.layoutManager = LinearLayoutManager(context)
        val listAdapter = ListAdapter()
        binding.rvList.adapter = listAdapter
        val user = Firebase.auth.currentUser
        binding.tvNama.text = user?.displayName
        listAdapter.setOnItemClickCallback(object: ListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: String) {
                if (data.equals("Keluar")) {
                    Firebase.auth.signOut()
                    startActivity(Intent(context, ActivityLogin::class.java))
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}