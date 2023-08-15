package jsondatabase.server

import jsondatabase.utils.Parser
import jsondatabase.utils.RequestType
import jsondatabase.Constants
import jsondatabase.utils.Utils.Companion.getKeys
import jsondatabase.utils.Utils.Companion.getType
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.InetAddress
import java.net.ServerSocket
import java.util.concurrent.Executors

class Server {
    private val serverSocket = ServerSocket(Constants.PORT, 50, InetAddress.getByName(Constants.ADDRESS))
    private val executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    private val parser = Parser()
    fun start() {
        println("Server started!")
        executor.submit {
            while (true) {
                val accept = serverSocket.accept()
                val input = DataInputStream(accept.getInputStream())
                val output = DataOutputStream(accept.getOutputStream())
                val request = parser.jsonToMap(input.readUTF())
                val type = getType(request["type"])
                val keys = getKeys(request["key"])
                val value = request["value"]
                when (type) {
                    RequestType.GET -> get(keys, output)
                    RequestType.SET -> set(keys, value, output)
                    RequestType.DELETE -> delete(keys, output)
                    RequestType.EXIT -> exit(output)
                }
            }
        }
    }

    private fun get(keys: Collection<String>, output: DataOutputStream) {
        try {
            output.writeUTF(parser.mapToJson(mapOf("response" to "OK", "value" to database.getValueForKeys(keys)!!)))
        } catch (e: RuntimeException) {
            output.writeUTF(parser.mapToJson(mapOf("response" to "ERROR", "reason" to "No such key")))
        }
    }

    private fun set(keys: Collection<*>, value: Any?, output: DataOutputStream) {
        try {
            if (value == null) throw RuntimeException()
            database.setValueForKeys(keys as List<String>, value)
            output.writeUTF(parser.mapToJson(mapOf("response" to "OK")))
        } catch (e: RuntimeException) {
            output.writeUTF(parser.mapToJson(mapOf("response" to "ERROR", "reason" to "Wrong format")))
        }

    }

    private fun delete(keys: Collection<*>, output: DataOutputStream) {
        try {
            database.deleteValueForKeys(keys as List<String>)
            output.writeUTF(parser.mapToJson(mapOf("response" to "OK")))
        } catch (e: RuntimeException) {
            output.writeUTF(parser.mapToJson(mapOf("response" to "ERROR", "reason" to "No such key")))
        }
    }

    private fun exit(output: DataOutputStream) {
        output.writeUTF(parser.mapToJson(mapOf("response" to "OK")))
        executor.shutdown()
        serverSocket.close()
    }

}