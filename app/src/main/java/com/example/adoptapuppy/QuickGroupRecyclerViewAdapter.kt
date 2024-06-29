package com.example.adoptapuppy
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.content.Context


class QuickGroupRecyclerViewAdapter (val context: Context) :
    RecyclerView.Adapter<QuickGroupRecyclerViewAdapter.ViewHolder>() {

        var items = emptyArray<QuickGroupItem>()
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.quick_group_item_text)
        val imageView = itemView.findViewById<ImageView>(R.id.quick_group_item_image)
        val container = itemView.findViewById<ViewGroup>(R.id.quick_group_item_parent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.quick_group_item, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.textView.text = item.text
        holder.imageView.setImageDrawable(item.image)
    }
}