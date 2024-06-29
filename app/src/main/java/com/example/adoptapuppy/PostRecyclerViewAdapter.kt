package com.example.adoptapuppy
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide


class PostRecyclerViewAdapter (val context: Context) :
    RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder>() {

        var items = emptyArray<Post>()
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView = itemView.findViewById<TextView>(R.id.post_item_title)
        val subtitleView = itemView.findViewById<TextView>(R.id.post_item_subtitle)
        val imageView = itemView.findViewById<ImageView>(R.id.post_item_imageview)
        val likeView = itemView.findViewById<ImageView>(R.id.like_icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.quick_group_item, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.titleView.text = item.name
        holder.subtitleView.text = "${item.age}y - ${item.breed}${if (item.trained) " - trained" else ""}"

        holder.likeView.setImageDrawable(
            if (item.liked) AppCompatResources.getDrawable(context, R.drawable.ic_favourite_fill)
            else AppCompatResources.getDrawable(context, R.drawable.ic_favourite_outline)
        )


        Glide.with(context).load(item.url).into(holder.imageView)
    }
}