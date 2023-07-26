package mx.mariovaldez.melicodechallenge.search.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.mariovaldez.melicodechallenge.search.domain.usecases.SearchProduct
import mx.mariovaldez.melicodechallenge.search.presentation.models.ProductUI
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class ProductListViewModel @Inject constructor(
    private val searchProduct: SearchProduct,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val query: String = savedStateHandle[QUERY_EXTRA] ?: ""
    private var products: List<ProductUI> = emptyList()

    private val _state: MutableStateFlow<State?> = MutableStateFlow(null)
    val state: StateFlow<State?> get() = _state

    fun setDefault() {
        _state.value = null
    }

    fun search() {
        if (products.isEmpty()) {
            viewModelScope.launch {
                _state.value = State.Loading
                kotlin.runCatching {
                    searchProduct(query)
                }.onSuccess { result ->
                    products = result
                    if (result.isEmpty()) {
                        _state.value = State.NotFound
                        Timber.d("Product is null")
                    } else {
                        _state.value = State.ShowData(result)
                    }
                }.onFailure {
                    Timber.d(it)
                    _state.value = State.Error
                }
            }
        } else {
            _state.value = State.ShowData(products)
        }
    }

    fun setDefaultState() {
        _state.value = State.Default
    }

    fun productsIsEmpty(): Boolean = products.isEmpty()

    sealed class State {
        object Default : State()
        data class ShowData(val data: List<ProductUI>) : State()
        object Loading : State()
        object Error : State()
        object NotFound : State()
    }

    companion object {
        const val QUERY_EXTRA = "query"
    }
}
