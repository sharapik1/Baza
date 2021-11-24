package com.example.katya21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.lang.Exception

class ProductActivity : AppCompatActivity() {
    private val productList = ArrayList<Product>()
    private lateinit var app: MyApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        app = applicationContext as MyApp
        val productRecyclerView = findViewById<RecyclerView>(R.id.productView)

        if (app.token.isNotEmpty()){
            HTTP.requestGET(
                "http://s4a.kolei.ru/Product",
                mapOf(
                    "token" to app.token
                )
            ){result, error ->
                if (result != null){
                    productList.clear()

                    val json = JSONObject(result)
                   if(!json.has("notice"))
                       throw Exception("Не верный формат, ожидался объект notice")
                    if (json.getJSONObject("notice").has("data") ){
                        val data = json.getJSONObject("notice").getJSONArray("data")
                        for (i in 0 until data.length()){
                            val item = data.getJSONObject(i)
                            productList.add(
                                Product(
                                    item.getInt("ID"),
                                    item.getString("Title"),
                                    item.getInt("ProductTypeID"),
                                    item.getInt("ArticleNumber"),
                                    item.getString("Description"),
                                    item.getString("Image"),
                                    item.getInt("ProductionPersonCount"),
                                    item.getInt("ProductionWorkshopNumber"),
                                    item.getInt("MinCostForAgent")
                                )
                            )
                        }
                        runOnUiThread {
                            // уведомляем визуальный элемент, что данные изменились
                            productRecyclerView.adapter?.notifyDataSetChanged()
                        }
                    }



                    }
                }
            }
        }


    }
