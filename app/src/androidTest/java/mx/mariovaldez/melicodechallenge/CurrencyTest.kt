package mx.mariovaldez.melicodechallenge

import mx.mariovaldez.melicodechallenge.ktx.parseCurrency
import mx.mariovaldez.melicodechallenge.ktx.parseToPercent
import org.junit.Assert
import org.junit.Test
import java.text.NumberFormat
import kotlin.math.roundToInt

class CurrencyTest {

    @Test
    fun testCurrency() {
        val number = NumberFormat.getNumberInstance().format(1002)
        Assert.assertEquals("$ $number", parseCurrency(1002))
    }

    @Test
    fun test_currency_with_zero() {
        val number = NumberFormat.getNumberInstance().format(0)
        Assert.assertEquals("$ $number", parseCurrency(0))
    }

    @Test
    fun test_parse_to_percent() {
        val realPrice: Number = 1199
        val originalPrice: Number = 1388

        val percent = (realPrice.toFloat() / originalPrice.toFloat()) * 100
        println(percent.roundToInt())
        Assert.assertEquals(percent.roundToInt().toString(), parseToPercent(realPrice, originalPrice))
    }
}
