package mx.mariovaldez.melicodechallenge.search.data.remote

import mx.mariovaldez.melicodechallenge.data.remote.services.ApiServices
import mx.mariovaldez.melicodechallenge.search.data.models.response.Categories
import mx.mariovaldez.melicodechallenge.search.data.models.response.Product
import javax.inject.Inject

internal class RemoteDataSource @Inject constructor(
    private val apiServices: ApiServices
) {

    suspend fun search(query: String): Product = apiServices.search(query)

    suspend fun categories(): List<Categories> = apiServices.categories()
}
