package stock.market.average.price.script

import java.math.BigDecimal
import java.math.RoundingMode

data class Stock(
    val name: String,
) {
    private var quantity: Int = 0
    private var totalValue: BigDecimal = BigDecimal.ZERO

    fun averagePrice(): BigDecimal =
        if (quantity == 0) {
            BigDecimal.ZERO
        } else {
            totalValue.divide(BigDecimal(quantity), 2, RoundingMode.HALF_EVEN)
        }

    fun plus(
        value: BigDecimal,
        quantity: Int,
    ) {
        this.quantity += quantity
        this.totalValue += value
    }
}
