package stock.market.average.price.script

import java.util.logging.Logger

private val log = Logger.getGlobal()

fun main() =
    fetch()
        .associate {
            it.name to it.averagePrice()
        }.let {
            log.info {
                it.toJson()
            }
        }
