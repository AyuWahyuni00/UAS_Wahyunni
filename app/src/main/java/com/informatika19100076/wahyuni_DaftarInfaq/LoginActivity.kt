package com.informatika19100076.wahyuni_DaftarInfaq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.informatika19100061.DIKKYRAHMADANA.daftarinfaq.network.koneksi
import retrofit2.Response
import javax.security.auth.callback.Callback

class LoginActivity : AppCompatActivity() {
    private lateinit var sessionPreferences: SessionPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_login)
        val btn_submit = null
        btn_submit.setOnClickListener {
            val userName = et_username.text.toString()
            val password = et_password.text.toString()

            if (userName.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Form tidak boleh kosong!", Toast.LENGTH_LONG).show()
            }else{
                actionData(userName, password)
            }
        }
        btn_clean.setOnClickListener {
            formClear()
        }
        tv_disini.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
    fun actionData(username : String, password : String){
        koneksi.service.logIn(username, password).enqueue(object : Callback<responseadmin> {
            override fun onFailure(call: Call<responseadmin>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(call: Call<responseadmin>, response:
            Response<responseadmin>) {
                if (response.isSuccessful){
                    val resbody = response.body()
                    val resStatus = resbody?.status
                    val resUserName = resbody?.data?.get(0)?.username
                    Log.d("pesan", resUserName.toString())
                    if (resStatus == true){
                        )
                        sessionPreferences.actionLogin(resUserName.toString())
                        val i = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(i)
                        finish()
                    }else if (resStatus == false){
                        Toast.makeText(this@LoginActivity, "Username atau Password Anda Salah!", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
    fun formClear(){
        et_username.text.clear()
        et_password.text.clear()
    }
}

class SessionPreferences(loginActivity: LoginActivity) {
    fun actionLogin(toString: Any) {

    }

}

class responseadmin {

    val data: Any
        get() {
            TODO()
        }
    val status: Any
        get() {
            TODO()
        }
}
