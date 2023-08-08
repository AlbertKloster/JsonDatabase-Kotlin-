# Stage 3/6: Simple online database
## Description
In this stage, build upon the functionality of the program that you wrote in the first stage. The server should be able to receive messages `get`, `set`, and `delete` with an index of the cell. You also need to extend the database to 1000 cells (`1`-`1000`).

There is no need to save files on the hard drive, so if the server reboots, all the data will be lost. The server should serve one client at a time in a loop, and the client should only send one request to the server, get one reply, and exit. After that, the server should wait for another connection.

## Objectives
Since the server cannot shut down by itself and testing requires the program to stop at a certain point, you should implement a way to stop the server. When the client sends `exit`, the server stops. In a normal situation, when there's no testing needed, you shouldn't allow this behavior.

To send a request to the server, the client should get all the information through command-line arguments. These arguments include the type of the request (`set`, `get`, or `delete`), the index of the cell, and, in the case of the `set` request, a text.

The arguments will be passed to the client in the following format:
```
-t set -i 148 -m Here is some text to store on the server
```

`-t` is the type of request, and `-i` is the index of the cell. `-m` is the value to save in the database: you only need it in case of a `set` request.

The server and the client are different programs that run separately. Your server should run from the main function of the <b>/server</b> package, and the client should run from the main method of the <b>/client</b> package.

## Example
The greater-than symbol followed by a space (`> `) represents the user input. Note that it's not part of the input.

<i>Starting the server:</i>
```
> java Main
Server started!
```

<i>Starting the clients:</i>
```
> java Main -t get -i 1
Client started!
Sent: get 1
Received: ERROR
```
```
> java Main -t set -i 1 -m "Hello world!"
Client started!
Sent: set 1 Hello world!
Received: OK
```
```
> java Main -t set -i 1 -m HelloWorld!
Client started!
Sent: set 1 HelloWorld!
Received: OK
```
```
> java Main -t get -i 1
Client started!
Sent: get 1
Received: HelloWorld!
```
```
> java Main -t delete -i 1
Client started!
Sent: delete 1
Received: OK
```
```
> java Main -t get -i 1
Client started!
Sent: get 1
Received: ERROR
```
```
> java Main -t exit
Client started!
Sent: exit
Received: OK
```
