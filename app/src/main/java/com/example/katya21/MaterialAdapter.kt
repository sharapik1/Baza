package com.example.katya21

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class MaterialAdapter (
    private val values: ArrayList<Material>,
    private val activity: Activity
        ): RecyclerView.Adapter<MaterialAdapter.ViewHolder>() {
    private var itemClickListener: ((Product) -> Unit)? = null
    fun setItemClickListener(itemClickListener: (Material) -> Unit) {
        this.itemClickListener = itemClickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.material_item,
                parent,
                false
            )
        return ViewHolder(itemView)
    }
    override  fun getItemCount(): Int = values.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = values[position].Title
        holder.description.text = values[position].Description
        holder.cost.text = values[position].Cost.toString()
        holder.container.setOnClickListener {
            //кликнули на элемент списка
            itemClickListener?.invoke(values[position])
        }
        val fileName = values[position].Image.split("\\").lastOrNull()
        Log.d("KEILOG",fileName?:"null")
        if(fileName!=null){
            HTTP.getImage("http://s4a.kolei.ru/img/${fileName}"){bitmap, error ->
                if(bitmap !=null)
                    activity.runOnUiThread {
                        try {
                            holder.image.setImageBitmap(bitmap)
                        } catch (e: Exception) {

                        }
                    }

                else
                    Log.d("KEILOG", error)
            }


        }
    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var image: ImageView = itemView.findViewById(R.id.material_icon)
        var title: TextView = itemView.findViewById(R.id.material_title)
        var description: TextView = itemView.findViewById(R.id.material_description)
        var cost: TextView = itemView.findViewById(R.id.material_cost)
    }

}