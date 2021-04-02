package com.example.a4kfullwalpapers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a4kfullwalpapers.R
import com.example.a4kfullwalpapers.ui.home.filter.createFilterForType
import com.example.a4kfullwalpapers.ui.home.filter.filtersList
import com.squareup.picasso.Picasso
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
import kotlinx.android.synthetic.main.item_filter_rv.view.*

class FilterImageAdapter(var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<FilterImageAdapter.VH>() {

    private var names = filtersList().names
    private var filters = filtersList().filters
    private var images = filtersList().images

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            itemView.title.text = names[position]

            Picasso.get().load(images[position]).placeholder(R.drawable.placeholder_image)
                .into(itemView.item_image)

            itemView.menu_image.setOnClickListener {
                onItemClickListener.onItemClick(
                    createFilterForType(
                        itemView.context,
                        filters[position]
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_filter_rv, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = names.size

    interface OnItemClickListener {
        fun onItemClick(gpuImageFilter: GPUImageFilter)
    }
}