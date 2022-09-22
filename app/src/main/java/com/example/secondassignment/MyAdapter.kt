package com.example.secondassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val dataList:MutableList<Item>, val onItemClick: (Item)-> Unit): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        val textView:TextView
        val imageView:ImageView
        val trashImageView:ImageView
        init {
            textView = view.findViewById(R.id.row_text)
            imageView = view.findViewById(R.id.row_image)
            trashImageView = view.findViewById(R.id.row_trash_image)
        }
    }

    fun addItem(item:Item){

        dataList.add(item)
        notifyItemInserted(dataList.size)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Item = dataList[position]
        holder.imageView.setImageResource(Item.getImage(Item.image))
        holder.trashImageView.setImageResource(R.drawable.trash)
        holder.textView.text = Item.title

        holder.trashImageView.setOnClickListener{
            dataList.removeAt(holder.layoutPosition)
            notifyItemRemoved(position)
        }

        holder.textView.setOnClickListener {
            onItemClick(dataList[position])
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}