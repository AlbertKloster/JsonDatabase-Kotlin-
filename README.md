# Stage 2/6: Network connection
## Description
Usually, online databases are accessed through the Internet. In this project, the database will be on your computer, but it will still be run as a separate program. The client who wants to get, create, or delete some information is a separate program, too.

We will use the Java `Socket` to connect to the database. A socket is an interface to send and receive data between different processes. These processes can be on the same computer or different computers connected through the Internet.

To connect to the server, the client must know its address, which consists of two parts: IP address and port. The address of your computer is always `127.0.0.1`. The port can be any number between `0` to `65535`, but preferably greater than `1024`.

Let's take a look at this client-side code:
```kotlin
val address = "127.0.0.1"
val port = 23456
val socket = Socket(InetAddress.getByName(address), port)
val input = DataInputStream(socket.getInputStream())
val output = DataOutputStream(socket.getOutputStream())
```

The client created a new socket, which means that the client tried to connect to the server. Successful creation of a socket means that the client found the server and managed to connect to it.

After that, you can see the creation of `DataInputStream` and `DataOutputStream` objects. These are the input and output streams to the server. If you expect data from the server, write `input.readUTF()`. This returns the string object that the server sent to the client. If you want to send data to the server, you need to write `output.writeUTF(stringText)`, and this message will be sent to the server.

Now let's look at the server-side code:
```kotlin
val address = "127.0.0.1"
val port = 23456
val server = ServerSocket(port, 50, InetAddress.getByName(address))
val socket = server.accept()
val input = DataInputStream(socket.getInputStream())
val output = DataOutputStream(socket.getOutputStream())
```

The server created a `ServerSocket` object that waits for client connections. When a client connects, the method `server.accept()` returns the Socket connection to this client. After that, you can see the creation of `DataInputStream` and `DataOutputStream` objects: these are the input and output streams to this client, now from the server side. To receive data from the client, write `input.readUTF()`. To send data to the client, write `output.writeUTF(stringText)`. The server should stop after responding to the client.

## Objectives
In this stage, implement the simplest connection between one server and one client. The client should send the server a message: something along the lines of `Give me a record # N`, where `N` is an integer number. The server should reply `A record # N was sent!` to the client. Both the client and the server should print the received messages to the console. Note that they exchange only these texts, not actual database files.

Before a client connects to the server, the server output should be `Server started!`.

Note: the server and the client are different programs that run separately. Your server should run from the main function of the <b>/server</b> package, and the client should run from the main method of the <b>/client</b> package. To test your program you should run the server first so a client can connect to the server.

## Example
The server should output something like this:
```
Server started!
Received: Give me a record # 12
Sent: A record # 12 was sent!
```

The client should output something like this:
```
Client started!
Sent: Give me a record # 12
Received: A record # 12 was sent!
```
