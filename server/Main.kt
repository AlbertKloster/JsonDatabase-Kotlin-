package jsondatabase.server

import jsondatabase.db.DataBase

val database = DataBase()

fun main() {
    val server = Server()
    server.start()
}
