package com.informatika19100076.wahyuni_DaftarInfaq

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import javax.security.auth.callback.Callback

class InsertDataActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)
        val toolbar =

            toolbar.title = "INSERT DATA"
        toolbar.setTitleTextColor(Color.WHITE)

        btn_submit.setOnClickListener {
            val etNamaBarang = et_nama_barang.text
            val etJmlBarang = et_jumlah_barang.text
            if (etJmlBarang.isEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "Jumlah Barang Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else if (etNamaBarang.isEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "Nama Barang Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val etnama_orang = null
                val ettanggal = null
                val etjumlah_uang = null
                val etketerangan = null
                actionData(etnama_orang.toString(), ettanggal.toString(), etjumlah_uang.toString(), etketerangan.toString())
            }
        }

        btn_clean.setOnClickListener {
            formClear()
        }
        getData()
    }

    fun formClear() {
//        val et_nama_orang = null
//        et_nama_orang.text.clear()
//        et_tanggal.text.clear()
//        et_jumlah_uang.text.clear()
//        et_keterangan.text.clear()

    }

    fun actionData(nama_orang: String, tanggal: String, jumlah_uang: String, keterangan: String) {
        koneksi.service.insertBarang(nama_orang, tanggal,jumlah_uang,keterangan)
            .enqueue(object : Callback<responactioninfak> {
                override fun onFailure(call: Call<responactioninfak>, t: Throwable) {
                    Log.d("pesan1", t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<responactioninfak>,
                    response: Response<responactioninfak>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@InsertDataActivity,
                            "data berhasil disimpan",
                            Toast.LENGTH_LONG
                        ).show()
                        formClear()
                        getData()
                    }
                }
            })
    }

    fun getData() {
        koneksi.service.getBarang().enqueue(object : Callback<responinfak> {
            override fun onFailure(call: Call<responinfak>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<responinfak>,
                response: Response<responinfak>
            ) {
                if (response.isSuccessful) {
                    val dataBody = response.body()
                    val datacontent = dataBody!!.data

                    val rvAdapter = ListContent(datacontent, this@InsertDataActivity, "InsertDataActivity")

                    rv_data_barang.apply {
                        var adapter = rvAdapter
                        var layoutManager = LinearLayoutManager(this@InsertDataActivity)
                    }
                }
            }
        })
    }

    class LinearLayoutManager(insertDataActivity: InsertDataActivity) {

    }

    class ListContent(datacontent: Any, insertDataActivity: InsertDataActivity, s: String) {

    }

    class responinfak {

        val data: Any
            get() {
                TODO()
            }
    }
}

fun <T> Call<T>.enqueue(callback: Callback<InsertDataActivity.responinfak>) {
    TODO("Not yet implemented")
}

