package jsondatabase.client

import jsondatabase.dto.SendDelete
import jsondatabase.dto.SendExit
import jsondatabase.dto.SendGet
import jsondatabase.dto.SendSet
import jsondatabase.utils.RequestType
import jsondatabase.utils.SocketConst
import jsondatabase.utils.Utils.Companion.getData
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.InetAddress
import java.net.Socket

class Client {
    private val clientSocket = Socket(InetAddress.getByName(SocketConst.address), SocketConst.port)

    fun start(args: Array<String>) {
        println("Client started!")
        val input = DataInputStream(clientSocket.getInputStream())
        val output = DataOutputStream(clientSocket.getOutputStream())
        val data = getData(args)
        val json = when (RequestType.getRequestType(data.type)) {
            RequestType.GET -> Json.encodeToString(SendGet(data.type, data.key))
            RequestType.SET -> Json.encodeToString(SendSet(data.type, data.key, data.value))
            RequestType.DELETE -> Json.encodeToString(SendDelete(data.type, data.key))
            RequestType.EXIT -> Json.encodeToString(SendExit(data.type))
        }

        output.writeUTF(json)
        println("Sent: $json")
        val response = input.readUTF()
        println("Received: $response")

        clientSocket.close()

    }

}