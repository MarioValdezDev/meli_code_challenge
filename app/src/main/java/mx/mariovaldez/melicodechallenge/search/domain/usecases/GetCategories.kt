package mx.mariovaldez.melicodechallenge.search.domain.usecases

import kotlinx.coroutines.withContext
import mx.mariovaldez.melicodechallenge.domain.dispatchers.DefaultDispatcherProvider
import mx.mariovaldez.melicodechallenge.search.data.repository.SearchRepository
import javax.inject.Inject

internal class GetCategories @Inject constructor(
    private val defaultDispatcherProvider: DefaultDispatcherProvider,
    private val repository: SearchRepository
) {

    suspend operator fun invoke() = withContext(defaultDispatcherProvider.default) {
        repository.categories()
    }
}
