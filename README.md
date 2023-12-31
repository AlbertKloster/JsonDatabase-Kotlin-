# Stage 1/6: 100-cell database
## Description
JSON database is a single file database that stores information in the form of JSON. It is a remote database, so it's usually accessed through the Internet.

In this stage, you need to simulate a database that can store text information in an array of size 100. From the start of the database, every cell contains an empty string. Users can save strings in the cells, read the information from these cells, and delete that information if needed. After a string has been deleted, that cell should contain an empty string.

The user can use the `set`, `get`, or `delete` commands.

## Objectives
- After `set`, a user should specify a number (`1`-`100`) and the text that will be saved to the cell. If the index is wrong, the program should output `ERROR`, otherwise, output `OK`. If the specified cell already contains information, it should be overwritten.
- After `get`, a user should specify the cell number from which they want to get information. If the cell is empty or the index is wrong, the program should output `ERROR`; otherwise, the program should output the content of the cell.
- After `delete`, the user should specify the number of the cell. If the index is wrong, the program should output `ERROR`; otherwise, output `OK`. If the string is empty, you don't have to do anything.
- To exit the program, a user should enter `exit`.

Your program should run from the main function of the <b>server</b> package.

## Example
The greater-than symbol followed by a space (`> `) represents the user input. Note that it's not part of the input.
```
> get 1
ERROR
> set 1 Hello world!
OK
> set 1 HelloWorld!
OK
> get 1
HelloWorld!
> delete 1
OK
> delete 1
OK
> get 1
ERROR
> set 55 Some text here
OK
> get 55
Some text here
> get 56
ERROR
> delete 55
OK
> delete 56
OK
> delete 100
OK
> delete 101
ERROR
> exit
```
