package jsondatabase.server

import jsondatabase.db.DataBase

val database = DataBase.getInstance()

fun main() {
    val server = Server()
    server.start()
}
