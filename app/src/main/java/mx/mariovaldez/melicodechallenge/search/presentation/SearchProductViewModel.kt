package mx.mariovaldez.melicodechallenge.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchProductViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableStateFlow<State?> = MutableStateFlow(null)
    val state: StateFlow<State?> get() = _state

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event> get() = _event

    fun showCancelButton(show: Boolean) {
        _state.value = State.IsShowCancelButton(show = show)
    }

    fun setDefault() {
        _state.value = State.Default
    }

    fun search(query: String) {
        viewModelScope.launch {
            _event.emit(Event.Search(query))
        }
    }

    sealed class State {

        data class IsShowCancelButton(val show: Boolean) : State()
        object Default : State()
    }

    sealed class Event {
        data class Search(val query: String) : Event()
    }
}
