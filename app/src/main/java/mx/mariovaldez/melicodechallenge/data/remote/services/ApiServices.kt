package mx.mariovaldez.melicodechallenge.data.remote.services

import retrofit2.http.GET
import retrofit2.http.Query

internal interface ApiServices {

    @GET("search")
    suspend fun search(@Query("q") query: String): Any
}
