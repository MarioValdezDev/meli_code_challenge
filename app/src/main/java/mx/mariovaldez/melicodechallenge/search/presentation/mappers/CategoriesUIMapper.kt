package mx.mariovaldez.melicodechallenge.search.presentation.mappers

import mx.mariovaldez.melicodechallenge.domain.mapper.Mapper
import mx.mariovaldez.melicodechallenge.search.data.models.response.Categories
import mx.mariovaldez.melicodechallenge.search.presentation.models.CategoriesUI
import javax.inject.Inject

internal class CategoriesUIMapper @Inject constructor() : Mapper<Categories, CategoriesUI> {
    override fun map(value: Categories): CategoriesUI = with(value) {
        CategoriesUI(
            id = id,
            name = name
        )
    }
}
