package de.hypar.hoffman_pixa.ui.imagelist

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import de.hypar.hoffman_pixa.databinding.ImageListFragmentBinding
import de.hypar.hoffman_pixa.models.ImageItem
import de.hypar.hoffman_pixa.util.dpToPx
import kotlinx.coroutines.launch

val TAG = ImageListFragment::class.java.simpleName

@AndroidEntryPoint
class ImageListFragment : Fragment() {

    companion object {
        fun newInstance() = ImageListFragment()
    }

    private val viewModel by viewModels<ImageListViewModel>()

    private var _binding: ImageListFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ImageListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        setupSearch()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.imageList.collect { list ->
                    bindUi(list)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun bindUi(imageList: List<ImageItem>) {
        val adapter = (binding.imageList.adapter as ImageListAdapter)
        adapter.items.clear()
        adapter.items.addAll(imageList)

        // We replace the whole list. NotifyDataSetChanged is appropriate
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupRecyclerView() {
        val recyclerView = binding.imageList
        val adapter = ImageListAdapter()
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

    private fun setupSearch() {
        binding.searchText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(tv: TextView?, action: Int, keyEvent: KeyEvent?): Boolean {
                // Handle "search" click on keyboard
                val donePressed = when (action) {
                    EditorInfo.IME_ACTION_DONE,
                    EditorInfo.IME_ACTION_SEARCH -> {
                        true
                    }
                    else -> {
                        false
                    }
                }

                // Handle hardware keyboard press on "enter"
                val enterPressed = keyEvent?.keyCode == KeyEvent.KEYCODE_ENTER
                        && keyEvent.action == KeyEvent.ACTION_DOWN

                if (donePressed || enterPressed) {

                    // Hide keyboard
                    val inputMethodService =
                        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodService.hideSoftInputFromWindow(tv?.windowToken, 0)

                    viewModel.searchImages(binding.searchText.editableText.toString())

                    return true
                }

                return false
            }
        })
    }
}