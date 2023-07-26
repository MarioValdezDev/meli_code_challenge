package mx.mariovaldez.melicodechallenge.search.presentation.mappers

import mx.mariovaldez.melicodechallenge.domain.mapper.Mapper
import mx.mariovaldez.melicodechallenge.search.data.models.response.Result
import mx.mariovaldez.melicodechallenge.search.presentation.models.InstallmentsUI
import mx.mariovaldez.melicodechallenge.search.presentation.models.ProductUI
import mx.mariovaldez.melicodechallenge.search.presentation.models.SellerUI
import mx.mariovaldez.melicodechallenge.search.presentation.models.ShippingUI
import javax.inject.Inject

internal class ProductUIMapper @Inject constructor() : Mapper<Result, ProductUI> {
    override fun map(value: Result): ProductUI = with(value) {
        ProductUI(
            id = id,
            title = title,
            condition = condition,
            thumbnailId = thumbnailId,
            catalogProductId = catalogProductId,
            listingTypeId = listingTypeId,
            permalink = permalink,
            buyingMode = buyingMode,
            siteId = siteId,
            categoryId = categoryId,
            domainId = domainId,
            thumbnail = thumbnail,
            currencyId = currencyId,
            orderBackend = orderBackend,
            price = price,
            originalPrice = originalPrice,
            soldQuantity = soldQuantity,
            availableQuantity = availableQuantity,
            officialStoreId = officialStoreId,
            useThumbnailId = useThumbnailId,
            acceptsMercadopago = acceptsMercadopago,
            shipping = ShippingUI(
                freeShipping = shipping.freeShipping
            ),
            seller = SellerUI(
                id = seller.id,
                name = seller.nickname
            ),
            installmentsUI = if (installments != null) {
                InstallmentsUI(
                    quantity = installments.quantity,
                    amount = installments.amount,
                    rate = installments.rate,
                    currencyId = installments.currencyId
                )
            } else {
                null
            }
        )
    }
}
