package jsondatabase.server

import java.lang.RuntimeException

enum class Commands(val string: String) {
    GET("get"),
    SET("set"),
    DELETE("delete"),
    EXIT("exit"),
    ;

    companion object {
        fun getCommand(input: String): Commands {
            for (command in Commands.values()) {
                if (command.string == input.lowercase()) return command
            }
            throw RuntimeException("Wrong command $input")
        }
    }
}