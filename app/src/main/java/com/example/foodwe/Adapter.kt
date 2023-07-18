package com.example.foodwe

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter(private val listener: onClicked): RecyclerView.Adapter<Adapter.ViewHolder>() {
    private val mlist:ArrayList<FoodData1> = ArrayList()

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.img1)
        val textView: TextView = itemView.findViewById(R.id.title)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.veritems, parent, false) // name of the layouut of how item should look in recycle view

        val viewHolder = ViewHolder(view) // by using view holder we can get clicked item adapter position
        view.setOnClickListener{
            listener.onItemClicked(mlist[viewHolder.adapterPosition]) // current pos of clicked item ,interfce method called
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = mlist[position]

        // sets the image to the imageview from our itemHolder class

        holder.textView.text= currentItem.title

        // Load the image using Glide

        // Load the image using Glide
        Glide.with(holder.itemView.context).load(currentItem.img).into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return mlist.size
    }

    fun updateData(updateData: List<FoodData1>){
        mlist.clear()
        mlist.addAll(updateData)

        // when below is called on overide function are called again and new news will be loaded
        notifyDataSetChanged()
    }
    interface  onClicked {
        fun onItemClicked(item:FoodData1)
    }
}