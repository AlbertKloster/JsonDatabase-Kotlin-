package jsondatabase.server

import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.InetAddress
import java.net.ServerSocket

class Server {
    private val serverSocket = ServerSocket(SocketConst.port, 50, InetAddress.getByName(SocketConst.address))

    fun start() {
        println("Server started!")
        val accept = serverSocket.accept()
        val input = DataInputStream(accept.getInputStream())
        val output = DataOutputStream(accept.getOutputStream())

        val data = input.readUTF()
        println("Received: $data")

        val recordNumber = data.split("#")[1].trim().toLong()

        val message = "A record # $recordNumber was sent!"
        output.writeUTF(message)
        println("Sent: A record # $recordNumber was sent!")
    }

    fun stop() {
        serverSocket.close()
    }

}