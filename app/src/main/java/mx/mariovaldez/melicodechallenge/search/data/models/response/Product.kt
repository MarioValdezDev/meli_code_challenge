package mx.mariovaldez.melicodechallenge.search.data.models.response

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("country_default_time_zone")
    val countryDefaultTimeZone: String,
    val query: String,
    val paging: Paging,
    val results: List<Result>
)

data class Paging(
    val total: Long,
    @SerializedName("primary_results")
    val primaryResults: Long,
    val offset: Long,
    val limit: Long
)

data class Result(
    val id: String,
    val title: String,
    val condition: String,
    @SerializedName("thumbnail_id")
    val thumbnailId: String,
    @SerializedName("catalog_product_id")
    val catalogProductId: String?,
    @SerializedName("listing_type_id")
    val listingTypeId: String,
    val permalink: String,
    @SerializedName("buying_mode")
    val buyingMode: String,
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("domain_id")
    val domainId: String,
    val thumbnail: String,
    @SerializedName("currency_id")
    val currencyId: String,
    @SerializedName("order_backend")
    val orderBackend: Long,
    val price: Number,
    @SerializedName("original_price")
    val originalPrice: Number?,
    @SerializedName("sold_quantity")
    val soldQuantity: Long,
    @SerializedName("available_quantity")
    val availableQuantity: Long,
    @SerializedName("official_store_id")
    val officialStoreId: Any?,
    @SerializedName("use_thumbnail_id")
    val useThumbnailId: Boolean,
    @SerializedName("accepts_mercadopago")
    val acceptsMercadopago: Boolean,
    val shipping: Shipping,
    val seller: Seller,
    val installments: Installments?
)

data class Shipping(
    val freeShipping: Boolean
)

data class Seller(
    val id: String,
    val nickname: String
)

data class Installments(
    val quantity: Long,
    val amount: Number,
    val rate: Number,
    @SerializedName("currency_id")
    val currencyId: String
)
