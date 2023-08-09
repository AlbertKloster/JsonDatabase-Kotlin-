package jsondatabase.utils

import jsondatabase.dto.SendBasic
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import java.io.File

class Utils {
    companion object {
        private const val PATH = "\\client\\data\\"
        fun getData(args: Array<String>): Data {
            val inIndex = args.indexOf("-in")
            return if (inIndex >= 0 && args.size > inIndex + 1)
                loadData(args[inIndex + 1])
            else
                Data(getType(args), getKey(args), getValue(args))
        }

        private fun loadData(filename: String): Data {
            return Json.decodeFromString<Data>(File(PATH + filename).readText())
        }

        fun getData(sendBasic: SendBasic) =
            Data(getType(sendBasic.type), sendBasic.key, sendBasic.value)

        private fun getType(type: String) = RequestType.getRequestType(type).string


        private fun getType(args: Array<String>): String {
            val requestTypeIndex = args.indexOf("-t")
            if (requestTypeIndex > -1 && requestTypeIndex + 1 <= args.size)
                return RequestType.getRequestType(args[requestTypeIndex + 1]).string
            throw RuntimeException("ERROR")
        }

        private fun getKey(args: Array<String>): String {
            val requestTypeIndex = args.indexOf("-k")
            if (requestTypeIndex > -1 && requestTypeIndex + 1 <= args.size)
                return args[requestTypeIndex + 1]
            return ""
        }

        private fun getValue(args: Array<String>): String {
            val requestTypeIndex = args.indexOf("-v")
            if (requestTypeIndex > -1 && requestTypeIndex + 1 <= args.size)
                return args[requestTypeIndex + 1]
            return ""
        }
    }

}