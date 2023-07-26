package mx.mariovaldez.melicodechallenge.search.data.repository

import dagger.Reusable
import mx.mariovaldez.melicodechallenge.search.data.remote.RemoteDataSource
import mx.mariovaldez.melicodechallenge.search.presentation.models.CategoriesUI
import mx.mariovaldez.melicodechallenge.search.presentation.models.ProductUI
import javax.inject.Inject

@Reusable
internal class SearchRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun search(query: String): List<ProductUI> = remoteDataSource.search(query)

    suspend fun categories(): List<CategoriesUI> = remoteDataSource.categories()
}
