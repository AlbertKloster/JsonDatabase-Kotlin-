package jsondatabase.utils

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

class Parser {
    fun jsonToMap(jsonString: String): Map<String, Any?> {
        return Json.decodeFromString<Map<String, @Serializable(with = AnySerializer::class) Any?>>(jsonString)
    }

    fun mapToJson(map: Map<String, Any?>): String {
        return Json.encodeToString<Map<String, @Serializable(with = AnySerializer::class) Any?>>(map)
    }

}