package mx.mariovaldez.melicodechallenge.data.remote.services

import mx.mariovaldez.melicodechallenge.search.data.models.response.Categories
import mx.mariovaldez.melicodechallenge.search.data.models.response.Product
import retrofit2.http.GET
import retrofit2.http.Query

internal interface ApiServices {

    @GET("search")
    suspend fun search(@Query("q") query: String): Product

    @GET("categories")
    suspend fun categories(): List<Categories>
}
