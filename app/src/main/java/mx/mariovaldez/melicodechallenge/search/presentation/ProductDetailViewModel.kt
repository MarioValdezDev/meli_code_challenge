package mx.mariovaldez.melicodechallenge.search.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import mx.mariovaldez.melicodechallenge.search.presentation.models.ProductUI
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val product: ProductUI? = savedStateHandle[PRODUCT_EXTRA]
    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Idle)
    val state: StateFlow<State> = _state

    fun showData() {
        _state.value =
            if (product != null) {
                State.ShowData(product)
            } else {
                Timber.d("Product is null")
                State.NotFound
            }
    }

    sealed class State {
        object Idle : State()
        data class ShowData(val product: ProductUI) : State()
        object NotFound : State()
    }

    companion object {
        const val PRODUCT_EXTRA = "PRODUCT_EXTRA"
    }
}
