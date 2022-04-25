package de.hypar.hoffman_pixa.ui.imagelist

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import de.hypar.hoffman_pixa.R
import de.hypar.hoffman_pixa.util.dpToPx

@AndroidEntryPoint
class ImageListFragment : Fragment() {

    companion object {
        fun newInstance() = ImageListFragment()
    }

    private val viewModel by viewModels<ImageListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.image_list_fragment, container, false)
        setupRecyclerView(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.image_list)
        val adapter = ImageListAdapter().apply {
            items.addAll(viewModel.imageList.value)
        }
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {

            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.set(0, 0, 0, 8.dpToPx(requireContext()))
            }
        })
    }
}