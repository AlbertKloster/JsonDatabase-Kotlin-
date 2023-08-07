package jsondatabase.client

import jsondatabase.server.SocketConst
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.InetAddress
import java.net.Socket

class Client {
    private val clientSocket = Socket(InetAddress.getByName(SocketConst.address), SocketConst.port)

    fun start() {
        println("Client started!")
        val input = DataInputStream(clientSocket.getInputStream())
        val output = DataOutputStream(clientSocket.getOutputStream())
        val recordNumber = 12
        val message = "Give me a record # $recordNumber"

        output.writeUTF(message)
        println("Sent: A record # $recordNumber was sent!")
        val data = input.readUTF()
        println("Received: $data")
    }

    fun stop() {
        clientSocket.close()
    }
}