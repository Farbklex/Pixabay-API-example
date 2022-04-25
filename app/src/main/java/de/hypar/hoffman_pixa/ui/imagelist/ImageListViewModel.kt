package de.hypar.hoffman_pixa.ui.imagelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.hypar.hoffman_pixa.BuildConfig
import de.hypar.hoffman_pixa.models.ImageItem
import de.hypar.hoffman_pixa.models.toImageItem
import de.hypar.pixabay_api.PixaBayApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    val imageApi: PixaBayApi
) : ViewModel() {


    private val _imageList: MutableStateFlow<List<ImageItem>> = MutableStateFlow(
        listOf()
    )

    val imageList: StateFlow<List<ImageItem>> = _imageList

    init {
        searchImages("fruit")
    }

    fun searchImages(query: String){
        viewModelScope.launch {
            // TODO: Api key should be more decoupled
            val response = imageApi.searchImages(BuildConfig.PIXABAY_API_KEY, query)

            if(response.isSuccessful){
                val imagesInResponse = response.body()?.hits?.map {
                        item -> item.toImageItem()
                }
                imagesInResponse?.let {
                    _imageList.value = it
                }
            }
        }
    }
}