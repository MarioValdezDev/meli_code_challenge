package mx.mariovaldez.melicodechallenge

import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import mx.mariovaldez.melicodechallenge.data.remote.mappers.ClientExceptionMapper
import mx.mariovaldez.melicodechallenge.data.remote.models.ResponseCallAdapterFactory
import mx.mariovaldez.melicodechallenge.data.remote.services.ApiServices
import mx.mariovaldez.melicodechallenge.search.data.models.response.Product
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class ServerTest {

    private lateinit var service: ApiServices

    @Before
    internal fun setUp() {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(ResponseCallAdapterFactory(ClientExceptionMapper(gson)))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ApiServices::class.java)
    }

    @Test
    fun get_products_is_not_null() = runBlocking {
        val products = service.search("laptop")
        Assert.assertNotNull(products.results)
    }

    @Test
    fun get_products_is_null() = runBlocking {
        val list = emptyList<Product>()
        val products = service.search("jaskmkefmeao")
        Assert.assertEquals(list, products.results)
    }

    @Test
    fun get_categories_is_not_null() = runBlocking {
        val categories = service.categories()
        Assert.assertNotNull(categories)
    }
}
