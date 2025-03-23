package stock.market.average.price.script

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream
import java.nio.file.Path

fun fetch(): Set<Stock> {
    val stocks = mutableMapOf<String, Stock>()

    FileInputStream(
        Path.of("src", "main", "resources", "trades.xlsx").toString(),
    ).use { inputStream ->
        XSSFWorkbook(inputStream)
            .getSheetAt(0)
            .drop(1)
            .filter {
                val purchase = "Compra"
                it.getCell(1)?.stringCellValue == purchase
            }.forEach { row ->
                val stockName = row.getCell(5)?.stringCellValue ?: return@forEach
                val quantity = row.getCell(6).numericCellValue.toInt()
                val valuePaid = row.getCell(8).numericCellValue.toBigDecimal()

                stocks
                    .getOrPut(stockName) { Stock(name = stockName) }
                    .plus(valuePaid, quantity)
            }
    }

    return stocks.values
        .toSet()
}
