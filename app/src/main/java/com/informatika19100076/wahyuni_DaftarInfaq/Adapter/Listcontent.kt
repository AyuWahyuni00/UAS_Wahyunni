package com.informatika19100076.wahyuni_DaftarInfaq.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.informatika19100061.DIKKYRAHMADANA.daftarinfaq.R
import com.informatika19100061.DIKKYRAHMADANA.daftarinfaq.model.DataItem

class ListContent(val ldata : List<DataItem?>?, val context: Context, val kondisi : String) :
    ListContent.RecyclerView.Adapter<ListContent.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        val namaBarang = view.findViewById<TextView>(R.id.tv_nama_barang)
        val jmlBarang = view.findViewById<TextView>(R.id.tv_jumlah_barang)
        val editBarang = view.findViewById<TextView>(R.id.tv_edit)
        val deleteBarang = view.findViewById<TextView>(R.id.tv_delete)

    }

    class RecyclerView {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_barang, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return ldata!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = ldata?.get(position)
//        holder.nama_orang.javaClass = model?.nama_orang
//        holder.tanggal.javaClass = model?.tanggal
//        holder.jumlah_uang.javaClass = model?.jumlah_uang
//        holder.keterangan.javaClass = model?.keterangan
        holder.editBarang.setOnClickListener {
            val i = Intent(context, UpdateDataActivity::class.java)
            i.putExtra("IDBARANG", model?.id)
            i.putExtra("NAMAORANG", model?.nama_orang)
            i.putExtra("TANGGAL", model?.tanggal)
            i.putExtra("JUMLAHUANG", model?.jumlah_uang)
            i.putExtra("KETERANGAN", model?.keterangan)
            context.startActivity(i)
        }
        holder.deleteBarang.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete" + model?.nama_orang)
                .setMessage("Apakah Anda Ingin Mengahapus Data Ini?")
                .setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->

                    koneksi.service.deleteBarang(model?.id).enqueue(object : Callback<responactioninfak>{
                        override fun onFailure(call: Call<responactioninfak>, t: Throwable) {
                            Log.d("pesan1", t.localizedMessage)
                        }

                        override fun onResponse(
                            call: Call<responactioninfak>,
                            response: Response<responactioninfak>
                        ) {
                            if(response.isSuccessful){
                                Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_LONG).show()
                                notifyDataSetChanged()
                                notifyItemRemoved(position)
                                notifyItemChanged(position)
                                notifyItemRangeChanged(position, ldata!!.size)

                                if(kondisi == " InsertDataActivity"){
                                    val activity = (context as InsertDataActivity)
                                    activity.getData()
                                }else if(kondisi == " UpdateDataActivity"){
                                    val activity = (context as UpdateDataActivity)
                                    activity.getData()
                                }else{
                                    val activity = (context as MainActivity)
                                    activity.getData()

                                }
                                Log.d("bpesan", response.body().toString())

                            }
                        }
                    })
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                    dialog.cancel()
                })
                .show()
        }
    }

    class responactioninfak {

    }


}
