package de.hypar.hoffman_pixa.ui.imagelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import de.hypar.hoffman_pixa.R

/**
 * RecyclerView Adapter for image tags
 */
class TagAdapter : RecyclerView.Adapter<TagAdapter.TagViewHolder>() {

    val items: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tag_list_item, parent, false)
        return TagViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.tag.text = items[position]
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    class TagViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tag: Chip = view.findViewById(R.id.tag)
    }
}
