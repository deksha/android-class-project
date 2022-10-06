package com.example.secondassignment

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(
    private val dataList:ArrayList<Item>,
    val onItemTitleClick: (Item)-> Unit,
    val onItemImageClick: (Item)-> Unit,
    val context: Context
    ): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

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
        val item = dataList[position]
        holder.textView.text = item.title
        holder.trashImageView.setImageResource(R.drawable.trash)
        if(item.imageType!=null){
            if(item.imageType==IMAGE_TYPE.URI){
                holder.imageView.setImageURI(Uri.parse(item.imagePath))
            }
            else{
                Glide.with(context).load(item.imagePath).into(holder.imageView);
            }
        }


        holder.trashImageView.setOnClickListener{
            dataList.removeAt(holder.layoutPosition)
            notifyItemRemoved(position)
        }

        holder.textView.setOnClickListener {
            onItemTitleClick(dataList[position])
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun adapterUpdateTheView(itemsList: List<Item>){
        dataList.clear()
        dataList.addAll(itemsList)
        notifyDataSetChanged()
    }
}