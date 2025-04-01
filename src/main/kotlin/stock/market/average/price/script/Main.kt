package stock.market.average.price.script

import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream
import java.nio.file.Path
import java.util.logging.Logger

private val log = Logger.getGlobal()

private val stocks = mutableMapOf<String, Stock>()

fun main() =
    fetchInputStream()
        .fetchDataLines()
        .filter { it.isPurchaseOperation() }
        .filterNotNull()
        .forEach { it.computeStockPurchaseOperation() }
        .run { showResult() }

private fun showResult() {
    stocks()
        .associate {
            it.simpleStockName() to it.averagePrice()
        }.let {
            log.info {
                it.toJson()
            }
        }
}

private fun stocks(): Set<Stock> =
    stocks.values
        .toSet()

private fun Row.computeStockPurchaseOperation() {
    val stockName = this.getCell(3)?.stringCellValue ?: return
    val quantity = this.getCell(5).numericCellValue.toInt()
    val valuePaid = this.getCell(7).numericCellValue.toBigDecimal()

    stocks
        .getOrPut(stockName) { Stock(name = stockName) }
        .plus(valuePaid, quantity)
}

fun fetchInputStream() = FileInputStream(sheetPath())

private fun FileInputStream.fetchDataLines(): List<Row?> =
    XSSFWorkbook(this)
        .getSheetAt(0)
        .drop(1)

private fun sheetPath(): String = Path.of("src", "main", "resources", "trades.xlsx").toString()

private fun Row?.isPurchaseOperation(): Boolean =
    (
        this?.getCell(0)?.stringCellValue == "Credito" &&
            this.getCell(2)?.stringCellValue == "Transferência - Liquidação"
    )
