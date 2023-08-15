package jsondatabase.client

import jsondatabase.utils.Parser
import jsondatabase.Constants
import jsondatabase.utils.Utils.Companion.getRequest
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.InetAddress
import java.net.Socket

class Client {
    private val clientSocket = Socket(InetAddress.getByName(Constants.ADDRESS), Constants.PORT)
    private val parser = Parser()

    fun start(args: Array<String>) {
        println("Client started!")
        val input = DataInputStream(clientSocket.getInputStream())
        val output = DataOutputStream(clientSocket.getOutputStream())
        val map = getRequest(args)
        val json = parser.mapToJson(map)

        output.writeUTF(json)
        println("Sent: $json")
        val response = input.readUTF()
        println("Received: $response")

        clientSocket.close()

    }

}