package stock.market.average.price.script

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.math.BigDecimal

fun Map<String, BigDecimal>.toJson(): String {
    val encoder =
        Json {
            serializersModule =
                SerializersModule {
                    contextual(BigDecimal::class, BigDecimalSerializer)
                }
            prettyPrint = true
            isLenient = true
            allowStructuredMapKeys = true
            coerceInputValues = true
            explicitNulls = false
        }

    return encoder.encodeToString(this)
}
