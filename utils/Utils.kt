package jsondatabase.utils

import jsondatabase.dto.SendBasic

class Utils {
    companion object {
        fun getData(args: Array<String>) = Data(getType(args), getKey(args), getValue(args))

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