package jsondatabase.utils

import java.lang.RuntimeException

enum class RequestType(val string: String) {
    GET("get"),
    SET("set"),
    DELETE("delete"),
    EXIT("exit"),
    ;

    companion object {
        fun getRequestType(input: String): RequestType {
            for (requestType in RequestType.values()) {
                if (requestType.string == input.lowercase()) return requestType
            }
            throw RuntimeException("Wrong command $input")
        }
    }
}