package mx.mariovaldez.melicodechallenge.ktx

import java.text.NumberFormat
import java.util.Locale
import kotlin.math.roundToInt

fun parseCurrency(price: Number): String = "$ ${
NumberFormat.getNumberInstance().run {
    maximumFractionDigits = 0
    format(price)
}
}"

fun parseToPercent(price: Number, originalPrice: Number): String =
    (originalPrice.toFloat() / (originalPrice.toFloat() - price.toFloat())).roundToInt()
        .toString()

fun parseToDiscountFormat(discount: String) = "$discount% OFF"

fun String.parseCapitalizedString(): String = this.lowercase(
    Locale.ROOT
).split(" ")
    .joinToString(" ") {
        it.replaceFirstChar { char ->
            if (char.isLowerCase()) {
                char.titlecase(Locale.ROOT)
            } else {
                it
            }
        }
    }
