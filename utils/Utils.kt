package jsondatabase.utils

import jsondatabase.Constants
import java.io.File

class Utils {
    companion object {
        private val parser = Parser()
        fun getRequest(args: Array<String>): Map<String, Any?> {
            val inIndex = args.indexOf("-in")
            return if (inIndex >= 0 && args.size > inIndex + 1) loadData(args[inIndex + 1])
            else getRequestFromArgs(args)
        }

        private fun getRequestFromArgs(args: Array<String>): Map<String, Any?> {
            val type = getType(args)
            val keys = getKeys(args)
            val value = getValue(args)
            val request = mutableMapOf<String, Any?>()
            request["type"] = type.string
            if (keys != null)
                request["key"] = keys
            if (value != null)
                request["value"] = value
            return request
        }

        private fun loadData(filename: String): Map<String, Any?> {
            return parser.jsonToMap(File(Constants.CLIENT_DATA_PATH + filename).readText())
        }

        fun getType(type: Any?): RequestType {
            if (type is String) {
                return RequestType.getRequestType(type)
            }
            throw RuntimeException("Wrong type")
        }

        fun getKeys(key: Any?): Collection<String> {
            return if (key is Collection<*>) {
                 key.map { it.toString() }
            } else
                listOf(key) as Collection<String>
        }


        private fun getType(args: Array<String>): RequestType {
            val requestTypeIndex = args.indexOf("-t")
            if (requestTypeIndex > -1 && requestTypeIndex + 1 <= args.size)
                return RequestType.getRequestType(args[requestTypeIndex + 1])
            throw RuntimeException("ERROR")
        }

        private fun getKeys(args: Array<String>): Any? {
            val requestTypeIndex = args.indexOf("-k")
            if (requestTypeIndex > -1 && requestTypeIndex + 1 <= args.size) {
                val json = "{\"key\":${args[requestTypeIndex + 1]}}"
                return parser.jsonToMap(json)["key"]
            }
            return null
        }

        private fun getValue(args: Array<String>): Any? {
            val requestTypeIndex = args.indexOf("-v")
            if (requestTypeIndex > -1 && requestTypeIndex + 1 <= args.size) {
                val json = "{\"value\":\"${args[requestTypeIndex + 1]}\"}"
                return parser.jsonToMap(json)["value"]
            }
            return null
        }
    }

}