package com.example.katya21

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.lang.Exception
class MaterialActivity : AppCompatActivity() {
    private val materialList = ArrayList<Material>()
    private lateinit var app: MyApp
    private lateinit var materialRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        app = applicationContext as MyApp



        materialRecyclerView = findViewById<RecyclerView>(R.id.materialView)
        if(app.token.isNotEmpty()) {
            HTTP.requestGET(
                "http://s4a.kolei.ru/Material",
                mapOf(
                    "token" to app.token
                )
            ) { result, error ->

                if (result != null)
                    try {
                        materialList.clear()
                        val json = JSONObject(result)
                        if (!json.has("notice"))
                            throw Exception("Не верный формат ответа, ожидался объект notice")
                        if(json.getJSONObject("notice").has("data")) {
                            val data = json.getJSONObject("notice").getJSONArray("data")
                            for (i in 0 until data.length()) {
                                val item = data.getJSONObject(i)
                                materialList.add(
                                    Material(
                                        item.getInt("ID"),
                                        item.getString("Title"),
                                        item.getInt("CountInPack"),
                                        item.getString("Unit"),
                                        item.getInt("CountInStock"),
                                        item.getInt("MinCount"),
                                        item.getString("Description"),
                                        item.getInt("Cost"),
                                        item.getString("Image"),
                                        item.getInt("MaterialTypeID")
                                    )
                                )
                            }


                            runOnUiThread {
                                materialRecyclerView.adapter?.notifyDataSetChanged()
                            }
                        }
                        else
                        {
                            throw Exception("Не верный формат ответа, ожидался объект token")
                        }
                    }
                    catch (e: Exception){
                        runOnUiThread {
                            AlertDialog.Builder(this)
                                .setTitle("Ошибка")
                                .setMessage(e.message)
                                .setPositiveButton("OK", null)
                                .create()
                                .show()
                        }
                    }
            }

        }
        else
            Toast.makeText(this, "Не найден токен, нужно залогиниться", Toast.LENGTH_LONG)
                .show()
        materialRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val materialAdapter = MaterialAdapter(materialList, this)
        materialAdapter.setItemClickListener {
            runOnUiThread{

            }
        }
        materialRecyclerView.adapter = materialAdapter
    }

    private fun showDetalisInfo(it: Material) {

    }
}