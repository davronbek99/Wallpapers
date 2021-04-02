package com.example.a4kfullwalpapers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a4kfullwalpapers.R
import com.example.a4kfullwalpapers.models.Photo
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_item.view.*
import java.lang.Exception

class ImageListPagingAdapter(var listener: OnImageItemClickListener) :
    PagedListAdapter<Photo, ImageListPagingAdapter.VH>(MyDiffUtil()) {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(photo: Photo) {
            Picasso.get().load(photo.src?.medium).placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_icon).into(itemView.image_view, object : Callback {
                    override fun onSuccess() {
                        itemView.progress.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        itemView.progress.visibility = View.GONE
                    }

                })
            itemView.setOnClickListener {
                listener.onImageItemClick(photo)
            }
        }
    }

    class MyDiffUtil() : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.equals(newItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position)!!)
    }

    interface OnImageItemClickListener {
        fun onImageItemClick(photo: Photo)
    }

}