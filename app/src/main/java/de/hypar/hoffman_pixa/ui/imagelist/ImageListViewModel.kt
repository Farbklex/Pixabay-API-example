package de.hypar.hoffman_pixa.ui.imagelist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import de.hypar.hoffman_pixa.models.ImageItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor() : ViewModel() {


    private val _imageList: MutableStateFlow<List<ImageItem>> = MutableStateFlow(
        listOf(
            ImageItem(
                1,
                listOf("Waterfall", "Nature", "NoFilter"),
                "",
                "",
                13,
                25,
                99,
                "Farbklex"
            ),
            ImageItem(
                1,
                listOf("Waterfall", "Nature", "NoFilter"),
                "",
                "",
                13,
                25,
                99,
                "Farbklex"
            ),
            ImageItem(
                1,
                listOf("Waterfall", "Nature", "NoFilter"),
                "",
                "",
                13,
                25,
                99,
                "Farbklex"
            )
        )
    )

    val imageList: StateFlow<List<ImageItem>> = _imageList
}