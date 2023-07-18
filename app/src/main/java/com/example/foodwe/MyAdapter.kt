package com.example.foodwe

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MyAdapter(private val listener: onItemClickedHor): RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    private val mlist:ArrayList<FoodData> = ArrayList()

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imgView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items, parent, false) // name of the layouut of how item should look in recycle view

        val viewHolder =
            ViewHolder(view) // by using view holder we can get clicked item adapter position
        view.setOnClickListener{
            listener.onItemClickKelyavar(mlist[viewHolder.adapterPosition]) // current pos of clicked item ,interfce method called
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
      return mlist.size
    }


    fun updateNews(updateNews: List<FoodData>){
        mlist.clear()
        mlist.addAll(updateNews)

        // when below is called on overide function are called again and new news will be loaded
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = mlist[position]

        // sets the image to the imageview from our itemHolder class


        // Load the image using Glide

        // Load the image using Glide
        Glide.with(holder.itemView.context).load(currentItem.image).into(holder.imageView)
    }

    interface onItemClickedHor{

        fun onItemClickKelyavar(item: FoodData)
    }
}