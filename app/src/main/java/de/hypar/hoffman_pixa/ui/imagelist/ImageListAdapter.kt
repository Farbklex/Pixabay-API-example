package de.hypar.hoffman_pixa.ui.imagelist

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hypar.hoffman_pixa.R
import de.hypar.hoffman_pixa.models.ImageItem
import de.hypar.hoffman_pixa.util.dpToPx

class ImageListAdapter : RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {

    val items: MutableList<ImageItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_list_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = items[position]
        holder.userName.text = item.user
        holder.tagList.adapter = TagAdapter().apply {
            items.addAll(item.tags)
            items.addAll(item.tags)
        }

        // Add spacing between tags
        if(holder.tagList.itemDecorationCount == 0){
            holder.tagList.addItemDecoration(object : RecyclerView.ItemDecoration() {

                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.set(0, 0, 8.dpToPx(holder.itemView.context), 0)
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val thumbnail: ImageView = view.findViewById(R.id.thumbnail)
        val userName: TextView = view.findViewById(R.id.username)
        val tagList: RecyclerView = view.findViewById(R.id.tag_list)
    }
}
