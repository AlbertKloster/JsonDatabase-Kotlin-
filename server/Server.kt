package jsondatabase.server

import jsondatabase.utils.RequestType
import jsondatabase.utils.SocketConst
import jsondatabase.utils.Utils.Companion.getData
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.InetAddress
import java.net.ServerSocket

class Server {
    private val serverSocket = ServerSocket(SocketConst.port, 50, InetAddress.getByName(SocketConst.address))

    fun start() {
        println("Server started!")
        while (true) {
            val accept = serverSocket.accept()
            val input = DataInputStream(accept.getInputStream())
            val output = DataOutputStream(accept.getOutputStream())
            val data = input.readUTF()
            val (requestType, id, string) = getData(data)
            when (requestType) {
                RequestType.GET -> get(id, output)
                RequestType.SET -> set(id, string, output)
                RequestType.DELETE -> delete(id, output)
                RequestType.EXIT -> exit(output)
            }
        }
    }

    private fun get(id: Long, output: DataOutputStream) {
        val cellById = database.getCellById(id)
        if (cellById == null || cellById.string.isEmpty())
            output.writeUTF("ERROR")
        else
            output.writeUTF(cellById.string)
    }

    private fun set(id: Long, text: String, output: DataOutputStream) {
        val cellById = database.getCellById(id)
        if (cellById == null)
            output.writeUTF("ERROR")
        else {
            cellById.string = text
            output.writeUTF("OK")
        }
    }

    private fun delete(id: Long, output: DataOutputStream) {
        val cellById = database.getCellById(id)
        if (cellById == null)
            output.writeUTF("ERROR")
        else {
            cellById.string = ""
            output.writeUTF("OK")
        }
    }

    private fun exit(output: DataOutputStream) {
        output.writeUTF("OK")
        serverSocket.close()
    }

}