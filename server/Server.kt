package jsondatabase.server

import jsondatabase.dto.*
import jsondatabase.utils.RequestType
import jsondatabase.utils.SocketConst
import jsondatabase.utils.Utils.Companion.getData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.InetAddress
import java.net.ServerSocket
import java.util.concurrent.Executors

class Server {
    private val serverSocket = ServerSocket(SocketConst.port, 50, InetAddress.getByName(SocketConst.address))
    private val executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    fun start() {
        println("Server started!")
        executor.submit {
            while (true) {
                val accept = serverSocket.accept()
                val input = DataInputStream(accept.getInputStream())
                val output = DataOutputStream(accept.getOutputStream())
                val sendBasic = Json.decodeFromString<SendBasic>(input.readUTF())
                val (type, key, value) = getData(sendBasic)
                when (type) {
                    RequestType.GET.string -> get(key, output)
                    RequestType.SET.string -> set(key, value, output)
                    RequestType.DELETE.string -> delete(key, output)
                    RequestType.EXIT.string -> exit(output)
                }
            }
        }
    }

    private fun get(key: String, output: DataOutputStream) {
        val valueByKey = database.getValueByKey(key)
        if (valueByKey.isNullOrEmpty())
            output.writeUTF(Json.encodeToString(Error("ERROR", "No such key")))
        else
            output.writeUTF(Json.encodeToString(ResponseGet("OK", valueByKey)))
    }

    private fun set(key: String, value: String, output: DataOutputStream) {
        database.setValueByKey(key, value)
        output.writeUTF(Json.encodeToString(ResponseSet("OK")))
    }

    private fun delete(key: String, output: DataOutputStream) {
        if (database.containsKey(key)) {
            database.removeByKey(key)
            output.writeUTF(Json.encodeToString(ResponseDelete("OK")))
        } else {
            output.writeUTF(Json.encodeToString(Error("ERROR", "No such key")))
        }
    }

    private fun exit(output: DataOutputStream) {
        output.writeUTF(Json.encodeToString(ResponseExit("OK")))
        executor.shutdown()
        serverSocket.close()
    }

}