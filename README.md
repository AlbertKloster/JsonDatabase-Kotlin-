# Stage 5/6: Multithreaded server
## Description
In this stage, improve your client and server by adding the ability to work with files. The server should keep the database on the hard drive file and update only after setting a new value or deleting one.

Let's think about another important aspect: when your database server becomes very popular, it won't be able to process a lot of requests because it can only process one request at a time. To avoid that, you can parallelize the server's work using executors so that every request is parsed and handled in a separate executor's task. The main thread should wait for incoming requests.

For this kind of functionality, you need <b>synchronization</b> because all your threads will work with the same file. Even after parallelizing, you need to preserve the integrity of the database. Of course, you can't write the file in two separate threads simultaneously, but if no one is currently writing to the file, a lot of threads can read it, and no one can interrupt the other since no one is modifying it. This behavior is implemented in `java.util.concurrent.locks.ReentrantReadWriteLock` class. It allows multiple readers of the resource but only a single writer. Once a writer locks the resource, it waits until all the readers finish reading and only then starts to write. The readers can freely read the file even though other readers locked it, but if the writer locks the file, no readers can read it.

To use this class, you need to import it with:
```kotlin
import java.util.concurrent.locks.*
```
Then, you need two locks: read lock and write lock. See the snippet below:
```kotlin
val lock: ReadWriteLock = ReentrantReadWriteLock()
val readLock: Lock = lock.readLock()
val writeLock: Lock = lock.writeLock()
```

Every time you want to read the file, invoke `readLock.lock()`. After reading, invoke `readLock.unlock()`. Do the same with `writeLock`, but only when you want to change the data.

Additionally, the client should have the ability to read a request from a local file. Here are some examples of the input file contents:
```json
{"type":"set","key":"name","value":"Kate"}
```
```json
{"type":"get","key":"name"}
```
```json
{"type":"delete","key":"name"}

```
## Objectives
- The server should keep the database on the hard drive in a `db.json` file which should be stored as the JSON file in the <i>/server/data</i> folder.
- Use executors at the server in order to simultaneously handle multiple requests. Writing to the database file should be protected by a lock as described in the description.
- Implement the ability to read a request from a file. If the `-in` argument is followed by the file name provided, read a request from that file. The file will be stored in <i>/client/data</i>.

## Example
The greater-than symbol followed by a space (`> `) represents the user input. Note that it's not part of the input.

<i>Starting the server:</i>
```
> java Main
Server started!
```

<i>Starting the clients:</i>
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
> java Main -in testSet.json
Client started!
Sent: {"type":"set","key":"name","value":"Kate"}
Received: {"response":"OK"}
```
```
> java Main -in testGet.json
Client started!
Sent: {"type":"get","key":"name"}
Received: {"response":"OK","value":"Kate"}
```
```
> java Main -in testDelete.json
Client started!
Sent: {"type":"delete","key":"name"}
Received: {"response":"OK"}
```
```
> java Main -t exit
Client started!
Sent: {"type":"exit"}
Received: {"response":"OK"}
```
