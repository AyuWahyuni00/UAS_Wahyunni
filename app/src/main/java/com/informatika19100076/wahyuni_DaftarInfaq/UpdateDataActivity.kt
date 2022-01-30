package com.informatika19100076.wahyuni_DaftarInfaq

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.informatika19100061.DIKKYRAHMADANA.daftarinfaq.adapter.ListContent
import com.informatika19100061.DIKKYRAHMADANA.daftarinfaq.network.koneksi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateDataActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data)
        val toolbar =

            toolbar.title = "UPDATE DATA"
        toolbar.setTitleTextColor(Color.WHITE)

        val i = intent
        val idBarang = i.getStringExtra("IDBARANG")
        val namabarang = i.getStringExtra("NAMABARANG")
        val jumlahbarang = i.getStringExtra("JUMLAHBARANG")

        et_nama_barang.setText(namabarang)
        et_jumlah_barang.setText(jumlahbarang)
        btn_submit.setOnClickListener {
            val etNamaBarang = et_nama_barang.text
            val etJmlBarang = et_jumlah_barang.text
            if (etJmlBarang.isEmpty()){
                Toast.makeText(this@UpdateDataActivity, "Jumlah Barang Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            }else if (etNamaBarang.isEmpty()){
                Toast.makeText(this@UpdateDataActivity, "Nama Barang Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            }else{
                actionData(idBarang.toString(), etNamaBarang.toString(), etJmlBarang.toString())
            }
        }
        btn_back.setOnClickListener {
            finish()
        }
        getData()
    }
    fun actionData(id : String, namaBarang : String, jmlBarang : String){
        koneksi.service.updateBarang(id, namaBarang, jmlBarang).enqueue(object :
            Callback<ListContent.responactioninfak> {
            override fun onFailure(call: Call<ListContent.responactioninfak>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ListContent.responactioninfak>,
                response: Response<ListContent.responactioninfak>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(this@UpdateDataActivity, "data berhasil diupdate", Toast.LENGTH_LONG).show()
                    getData()
                }
            }
        })
    }
    fun getData(){
        koneksi.service.getBarang().enqueue(object : Callback<InsertDataActivity.responinfak>{
            override fun onFailure(call: Call<InsertDataActivity.responinfak>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<InsertDataActivity.responinfak>,
                response: Response<InsertDataActivity.responinfak>
            ) {
                if (response.isSuccessful){
                    val dataBody = response.body()
                    val datacontent = dataBody!!.data

                    val rvAdapter = ListContent(datacontent, this@UpdateDataActivity, "UpdateDataActivity")


                    rv_data_barang.apply {
                        adapter = rvAdapter
                        layoutManager = LinearLayoutManager(this@UpdateDataActivity)
                    }

                }
            }

        })
    }

    private fun ListContent(
        datacontent: Any,
        updateDataActivity: UpdateDataActivity,
        s: String
    ): RecyclerView.Adapter<RecyclerView.ViewHolder>? {
        TODO("Not yet implemented")
    }
