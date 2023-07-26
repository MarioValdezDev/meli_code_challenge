package mx.mariovaldez.melicodechallenge.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import mx.mariovaldez.melicodechallenge.search.presentation.models.ProductUI
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor() : ViewModel() {
    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event> get() = _event

    fun onRequirementCompleted(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    sealed class Event {

        abstract val type: EventType

        data class NavigateToSearchRequirement(
            override val type: EventType = EventType.NAVIGATION
        ) : Event()

        data class NavigateToProductListRequirement(
            val query: String,
            override val type: EventType = EventType.NAVIGATION
        ) : Event()

        data class NavigateToProductDetailRequirement(
            val productUI: ProductUI,
            override val type: EventType = EventType.NAVIGATION
        ) : Event()
    }

    enum class EventType {

        NAVIGATION
    }
}
