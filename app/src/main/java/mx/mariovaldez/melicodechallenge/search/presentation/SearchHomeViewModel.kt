package mx.mariovaldez.melicodechallenge.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.mariovaldez.melicodechallenge.search.domain.usecases.GetCategories
import mx.mariovaldez.melicodechallenge.search.presentation.models.CategoriesUI
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class SearchHomeViewModel @Inject constructor(
    private val getCategories: GetCategories
) : ViewModel() {

    private val _state: MutableStateFlow<State?> = MutableStateFlow(null)
    val state: StateFlow<State?> get() = _state

    fun fetchCategories() {
        viewModelScope.launch {
            kotlin.runCatching {
                getCategories()
            }
                .onSuccess {
                    Timber.d("Success  $it")
                    _state.value = State.ShowData(it)
                }
                .onFailure {
                    Timber.d("Error: $it")
                    _state.value = State.Error
                }
        }
    }

    sealed class State {
        data class ShowData(val data: List<CategoriesUI>) : State()
        object Error : State()
    }
}
