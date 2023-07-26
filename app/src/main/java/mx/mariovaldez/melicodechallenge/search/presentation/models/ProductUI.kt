package mx.mariovaldez.melicodechallenge.search.presentation.models

import java.io.Serializable

data class ProductUI(
    val id: String,
    val title: String,
    val condition: String,
    val thumbnailId: String,
    val catalogProductId: String?,
    val listingTypeId: String,
    val permalink: String,
    val buyingMode: String,
    val siteId: String,
    val categoryId: String,
    val domainId: String,
    val thumbnail: String,
    val currencyId: String,
    val orderBackend: Long,
    val price: Number,
    val originalPrice: Number?,
    val soldQuantity: Long,
    val availableQuantity: Long,
    val officialStoreId: Any?,
    val useThumbnailId: Boolean,
    val acceptsMercadopago: Boolean,
    val shipping: ShippingUI,
    val seller: SellerUI,
    val installmentsUI: InstallmentsUI?
) : Serializable

data class ShippingUI(
    val freeShipping: Boolean
)

data class SellerUI(
    val id: String,
    val name: String
)

data class InstallmentsUI(
    val quantity: Long,
    val amount: Number,
    val rate: Number,
    val currencyId: String
)
