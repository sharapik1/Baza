package com.example.katya21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LoginDialog { login, password ->
            Log.d("KEILOG", "${login}/${password}")
        }.show(supportFragmentManager, null)


    }

}