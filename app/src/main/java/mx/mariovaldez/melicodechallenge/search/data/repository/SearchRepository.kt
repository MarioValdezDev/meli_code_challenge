package mx.mariovaldez.melicodechallenge.search.data.repository

import dagger.Reusable
import mx.mariovaldez.melicodechallenge.search.data.remote.RemoteDataSource
import mx.mariovaldez.melicodechallenge.search.presentation.mappers.CategoriesUIMapper
import mx.mariovaldez.melicodechallenge.search.presentation.mappers.ProductUIMapper
import mx.mariovaldez.melicodechallenge.search.presentation.models.CategoriesUI
import mx.mariovaldez.melicodechallenge.search.presentation.models.ProductUI
import javax.inject.Inject

@Reusable
internal class SearchRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val productUIMapper: ProductUIMapper,
    private val categoriesUIMapper: CategoriesUIMapper,
) {

    suspend fun search(query: String): List<ProductUI> = productUIMapper.map(remoteDataSource.search(query).results)

    suspend fun categories(): List<CategoriesUI> = categoriesUIMapper.map(remoteDataSource.categories())
}
