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

class ProductAdapter (
    private val values: ArrayList<Product>,
    private val activity: Activity
): RecyclerView.Adapter<ProductAdapter.ViewHolder>(){
    private var itemClickListener: ((Product) -> Unit)? = null
    fun setItemClickListener(itemClickListener: (Product) -> Unit) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.product_item,
                parent,
                false)
        return ViewHolder(itemView)
    }
    override  fun getItemCount(): Int = values.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.title.text = values[position].Title
        holder.article.text = values[position].ArticleNumber.toString()
        holder.price.text = values[position].MinCostForAgent.toString()
        holder.number.text = values[position].ProductionWorkshopNumber.toString()
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
        var image: ImageView = itemView.findViewById(R.id.image)
        var title: TextView = itemView.findViewById(R.id.title)
        var article: TextView = itemView.findViewById(R.id.article)
        var  price: TextView = itemView.findViewById(R.id.mincost)
        var number: TextView = itemView.findViewById(R.id.workshopNumber)
        var container: LinearLayout = itemView.findViewById(R.id.container)
    }

}