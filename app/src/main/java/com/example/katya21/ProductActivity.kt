package com.example.katya21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class ProductActivity : AppCompatActivity() {
    var token = ""
val resultTextView = findViewById<TextView>(R. id. result)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        if(token.isNotEmpty()){
            HTTP.requestGET(
                    "http://s4a.kolei.ru/Product",
                    mapOf(
                            "token" to token
                    )
            ){result, error ->
                runOnUiThread{
                    if(result!=null){
                        resultTextView.text = result
                    }
                    else
                        resultTextView.text = "ошибка: $error"
                }
            }
        }
        else
            Toast.makeText(this, "Не найден токен, нужно залогиниться", Toast.LENGTH_LONG)
                    .show()

    }
}