package mx.mariovaldez.melicodechallenge.search.data.remote

import mx.mariovaldez.melicodechallenge.data.remote.services.ApiServices
import mx.mariovaldez.melicodechallenge.search.presentation.mappers.CategoriesUIMapper
import mx.mariovaldez.melicodechallenge.search.presentation.mappers.ProductUIMapper
import mx.mariovaldez.melicodechallenge.search.presentation.models.CategoriesUI
import mx.mariovaldez.melicodechallenge.search.presentation.models.ProductUI
import javax.inject.Inject

internal class RemoteDataSource @Inject constructor(
    private val apiServices: ApiServices,
    private val productUIMapper: ProductUIMapper,
    private val categoriesUIMapper: CategoriesUIMapper
) {

    suspend fun search(query: String): List<ProductUI> =
        productUIMapper.map(apiServices.search(query).results)

    suspend fun categories(): List<CategoriesUI> =
        categoriesUIMapper.map(apiServices.categories())
}
