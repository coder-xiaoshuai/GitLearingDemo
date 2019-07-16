package com.example.zhangshuai.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zhangshuai.gitlearingdemo.R

class ListSeeMoreAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    companion object {
        private const val ITEM_TYPE_NORMAL = 0x01
        private const val ITEM_TYPE_SEE_MORE = 0x02
    }


    private val list: ArrayList<String>
    private var context: Context? = null

    constructor(list: ArrayList<String>, context: Context) {
        this.list = list
        this.context = context

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ITEM_TYPE_NORMAL -> {
                return NormalViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv_type_normal, parent, false))
            }
            ITEM_TYPE_SEE_MORE -> {
                return SeeMoreViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv_type_see_more, parent, false))
            }
        }
        return return NormalViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv_type_normal, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM_TYPE_NORMAL -> {
                holder as NormalViewHolder
                holder.textView.text = "222222"
                if (position%2 == 0){
                    holder.imageView.setBackgroundColor(Color.BLUE)
                }else{
                    holder.imageView.setBackgroundColor(Color.CYAN)
                }

            }
            ITEM_TYPE_SEE_MORE -> {
                holder as SeeMoreViewHolder
                holder.textView.text = "查看更多更多"
            }
        }
    }


    class NormalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val textView: TextView = itemView.findViewById(R.id.text)
    }

    class SeeMoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text_see_more)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount-1) ITEM_TYPE_SEE_MORE else ITEM_TYPE_NORMAL
    }
}
