package enums.mapper


import enums.model.EnumIntegerModel
import enums.model.EnumIntegerValue
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors


/**
 * @author yannishin
 */
class EnumIntegerMapper {
    private val factory: MutableMap<String, List<EnumIntegerValue>> = HashMap<String, List<EnumIntegerValue>>()
    private fun toEnumValues(e: Class<out EnumIntegerModel?>): List<EnumIntegerValue> {
        return Arrays.stream(e.enumConstants)
                .map({ EnumIntegerValue(it!!) })
                .collect(Collectors.toList())
    }

    fun put(key: String, e: Class<out EnumIntegerModel?>) {
        factory[key] = toEnumValues(e)
    }

    val all: Map<String, List<Any>>
        get() = factory

    operator fun get(keys: String): Map<String, List<EnumIntegerValue>?> {
        return Arrays
                .stream(keys.split(",").toTypedArray())
                .collect(Collectors.toMap<String, String, List<EnumIntegerValue>?>(Function.identity(), Function<String, List<EnumIntegerValue>?> { key: String? -> factory.get(key) }))
    }
}

