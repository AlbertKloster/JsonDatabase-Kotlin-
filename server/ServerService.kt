package jsondatabase.server

class ServerService {

    fun run() {
        var exit = false
        while (!exit) {
            val (command, id, string) = getInput()
            when (command) {
                Commands.GET -> get(id)
                Commands.SET -> set(id, string)
                Commands.DELETE -> delete(id)
                Commands.EXIT -> exit = true
            }
        }
    }

    private fun getInput(): Input {
        val strings = readln().trim().split(Regex(" +"), 3)
        return when (strings.size) {
            1 -> Input(Commands.getCommand(strings[0]))
            2 -> Input(Commands.getCommand(strings[0]), strings[1].toLong())
            3 -> Input(Commands.getCommand(strings[0]), strings[1].toLong(), strings[2])
            else -> throw RuntimeException("ERROR")
        }
    }

    private fun get(id: Long) {
        val cellById = database.getCellById(id)
        if (cellById == null || cellById.string.isEmpty())
            println("ERROR")
        else
            println(cellById.string)
    }

    private fun set(id: Long, text: String) {
        val cellById = database.getCellById(id)
        if (cellById == null)
            println("ERROR")
        else {
            cellById.string = text
            println("OK")
        }
    }

    private fun delete(id: Long) {
        val cellById = database.getCellById(id)
        if (cellById == null)
            println("ERROR")
        else {
            cellById.string = ""
            println("OK")
        }
    }
}