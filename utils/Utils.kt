package jsondatabase.utils

import java.lang.RuntimeException

class Utils {
    companion object {
        fun getData(args: Array<String>) = Data(getRequestType(args), getId(args), getMessage(args))

        fun getData(string: String): Data {
            val split = string.split(Regex(" +"), 3)
            val tags = listOf("-t", "-i", "-m")
            val args = mutableListOf<String>()
            for (i in split.indices) {
                args.add(tags[i])
                args.add(split[i])
            }
            return Data(getRequestType(args.toTypedArray()), getId(args.toTypedArray()), getMessage(args.toTypedArray()))
        }

        private fun getRequestType(args: Array<String>): RequestType {
            val requestTypeIndex = args.indexOf("-t")
            if (requestTypeIndex > -1 && requestTypeIndex + 1 <= args.size)
                return RequestType.getRequestType(args[requestTypeIndex + 1])
            throw RuntimeException("ERROR")
        }

        private fun getId(args: Array<String>): Long {
            val requestTypeIndex = args.indexOf("-i")
            if (requestTypeIndex > -1 && requestTypeIndex + 1 <= args.size)
                return args[requestTypeIndex + 1].toLong()
            return 0
        }

        private fun getMessage(args: Array<String>): String {
            val requestTypeIndex = args.indexOf("-m")
            if (requestTypeIndex > -1 && requestTypeIndex + 1 <= args.size)
                return args[requestTypeIndex + 1]
            return ""
        }
    }

}