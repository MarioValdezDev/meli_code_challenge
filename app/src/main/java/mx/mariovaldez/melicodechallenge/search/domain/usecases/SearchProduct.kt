package mx.mariovaldez.melicodechallenge.search.domain.usecases

import kotlinx.coroutines.withContext
import mx.mariovaldez.melicodechallenge.domain.dispatchers.DefaultDispatcherProvider
import mx.mariovaldez.melicodechallenge.search.data.repository.SearchRepository
import mx.mariovaldez.melicodechallenge.search.presentation.models.ProductUI
import javax.inject.Inject

internal class SearchProduct @Inject constructor(
    private val searchRepository: SearchRepository,
    private val defaultDispatcherProvider: DefaultDispatcherProvider
) {

    suspend operator fun invoke(query: String): List<ProductUI> =
        withContext(defaultDispatcherProvider.default) {
            searchRepository.search(query)
        }
}
