package enums.mapper

import enums.model.EnumStringModel
import enums.model.EnumStringValue
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors

class EnumStringMapper {
    private val factory: MutableMap<String, List<EnumStringValue>> = HashMap<String, List<EnumStringValue>>()
    private fun toEnumValues(e: Class<out EnumStringModel?>): List<EnumStringValue> {
        return Arrays.stream(e.enumConstants)
                .map{ EnumStringValue(it!!)}
                .collect(Collectors.toList())
    }

    fun put(key: String, e: Class<out EnumStringModel?>) {
        factory[key] = toEnumValues(e)
    }

    val all: Map<String, List<Any>>
        get() = factory

    operator fun get(keys: String): Map<String, List<EnumStringValue>?> {
        return Arrays
                .stream(keys.split(",").toTypedArray())
                .collect(Collectors.toMap<String, String, List<EnumStringValue>?>(Function.identity(), Function<String, List<EnumStringValue>?> { key: String? -> factory.get(key) }))
    }
}
