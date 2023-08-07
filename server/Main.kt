package jsondatabase.server

val database = DataBase.getInstance()

fun main() {
    val server = Server()
    server.start()
    server.stop()

}
