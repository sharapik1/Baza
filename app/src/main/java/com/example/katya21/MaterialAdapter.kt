package com.example.katya21

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class MaterialAdapter (
    private val values: ArrayList<Material>,
    private val activity: Activity
        ): RecyclerView.Adapter<MaterialAdapter.ViewHolder>() {

    private var itemClickListener: ((Material) -> Unit)? = null
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
        holder.cost.text = values[position].Cost.toString()
        holder.CountInPack.text = values[position].CountInPack.toString()
        holder.Unit.text = values[position].Unit
        holder.CountInStock.text = values[position].CountInStock.toString()
        holder.container.setOnClickListener {
            //кликнули на элемент списка
            itemClickListener?.invoke(values[position])
        }

    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var title: TextView = itemView.findViewById(R.id.material_title)
        var cost: TextView = itemView.findViewById(R.id.material_cost)
        var CountInPack: TextView = itemView.findViewById(R.id.material_CountInPack)
        var Unit: TextView = itemView.findViewById(R.id.material_Unit)
        var CountInStock: TextView = itemView.findViewById(R.id.material_CountInStock)
        var container: LinearLayout = itemView.findViewById(R.id.container)
    }

}