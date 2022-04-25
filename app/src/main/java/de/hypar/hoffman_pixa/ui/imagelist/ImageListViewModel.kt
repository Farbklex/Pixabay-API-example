package de.hypar.hoffman_pixa.ui.imagelist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.hypar.hoffman_pixa.BuildConfig
import de.hypar.hoffman_pixa.models.ImageItem
import de.hypar.hoffman_pixa.models.toImageItem
import de.hypar.pixabay_api.PixaBayApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

private val TAG = ImageListViewModel::class.java.simpleName

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
        val coroutineExceptionHanlder = CoroutineExceptionHandler{_, throwable ->
            Log.e(TAG, "Failed to query API", throwable)
        }
        viewModelScope.launch(coroutineExceptionHanlder) {
            // TODO: Data delivery should be handled by a separate repository class. The repository
            // should also manage caching
            // TODO: Api key should be more decoupled
            val response = imageApi.searchImages(BuildConfig.PIXABAY_API_KEY, query)

            if(response.isSuccessful){
                val imagesInResponse = response.body()?.hits?.map {
                        item -> item.toImageItem()
                }
                imagesInResponse?.let {
                    _imageList.value = it
                }
            } else {
                // TODO: Errors should show a message on UI
                Log.e(TAG, "Failed to query API")
            }
        }
    }
}