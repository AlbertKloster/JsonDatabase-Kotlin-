package jsondatabase.client

import jsondatabase.utils.SocketConst
import jsondatabase.utils.Utils.Companion.getData
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
        val requestType = data.requestType
        val id = data.id
        val string = data.string
        val request = if (id == 0L) requestType.string
        else if (string.isEmpty()) requestType.string + " " + id
        else requestType.string + " " + id + " " + string

        output.writeUTF(request)
        println("Sent: $request")
        val response = input.readUTF()
        println("Received: $response")
        clientSocket.close()

    }

}