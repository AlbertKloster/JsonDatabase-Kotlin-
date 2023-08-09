# Stage 4/6: Hello, JSON
## Description
In this stage, you will store the database in JSON format. To work with JSON, we recommend using the `kotlinx.serialization` library. It is also included in our project setup.

In this stage, store the database as a Java JSON object.

The keys should be strings (no more limited integer indexes), and the values should be strings, as well.

Example of JSON database:
```json
{
    "key1": "String value",
    "key2": 2,
    "key3": true
}
```

Also, you should send to the server a valid JSON (as a string) which includes all the parameters needed to execute the request.

Below are a few examples of the `set`, `get`, and `delete` requests and the `OK` and `ERROR` responses in JSON format.

Here is what the `set` request format should look like:
```json
{ "type": "set", "key": "Secret key", "value": "Secret value" }
```
The responses should be in JSON format. Please consider the examples below:
```json
{ "response": "OK" }
```

The `get` request:
```json
{ "type": "get", "key": "Secret key" }
```

The `delete` request:
```json
{ "type": "delete", "key": "Key that doesn't exist" }
```

In the case of a `get` request with a key stored in the database:
```
{ "response": "OK", "value": "Secret value" }

```

In the case of a `get` or `delete` request with a key that doesn't exist:
```json
{ "response": "ERROR", "reason": "No such key" }

```

## Objectives
Implement a Java JSON object to store the database records.

Implement the `set`, `get`, and `delete` requests and the `OK` and `ERROR` responses. Don't worry about multiple lines: the `kotlinx.serialization` library can represent them as a single line. Also, don't worry about extra spaces before and after quotes.

The arguments will be passed to the client in the following format:
```
-t set -k "Some key" -v "Here is some text to store on the server"
```
`-t` is the type of request, and `-k` is the key. `-v` is the value to save in the database: you only need it in case of a `set` request.

## Example
The greater-than symbol followed by a space (`> `) represents the user input. Note that it's not part of the input.

<i>Starting the server:</i>
```
> java Main
Server started!
```

Starting the clients:
```
> java Main -t get -k 1
Client started!
Sent: {"type":"get","key":"1"}
Received: {"response":"ERROR","reason":"No such key"}
```
```
> java Main -t set -k 1 -v HelloWorld!
Client started!
Sent: {"type":"set","key":"1","value":"HelloWorld!"}
Received: {"response":"OK"}
```
```
> java Main -t get -k 1
Client started!
Sent: {"type":"get","key":"1"}
Received: {"response":"OK","value":"HelloWorld!"}
```
```
> java Main -t delete -k 1
Client started!
Sent: {"type":"delete","key":"1"}
Received: {"response":"OK"}
```
```
> java Main -t delete -k 1
Client started!
Sent: {"type":"delete","key":"1"}
Received: {"response":"ERROR","reason":"No such key"}
```
```
> java Main -t get -k 1
Client started!
Sent: {"type":"get","key":"1"}
Received: {"response":"ERROR","reason":"No such key"}
```
```
> java Main -t exit
Client started!
Sent: {"type":"exit"}
Received: {"response":"OK"}
```
