package com.example.laundryappproject.data

import android.content.Context
import android.content.res.TypedArray
import com.example.laundryappproject.R

object DataDummy {
    private lateinit var listofnama_laundry: Array<String>
    private lateinit var listoflokasi: Array<String>
    private lateinit var listoftanggal: Array<String>
    private lateinit var listofavatar: TypedArray

    private fun prepareData(context: Context) {
        listofnama_laundry = context.resources.getStringArray(R.array.nama_laundry)
        listoflokasi = context.resources.getStringArray(R.array.lokasi)
    }



}