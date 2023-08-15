package jsondatabase.server

import jsondatabase.db.DataBase
import jsondatabase.utils.Parser

val database = DataBase()

fun main() {

    val server = Server()
    server.start()

//    val jsonParser = Parser()
//    val input = "{\"type\":\"set\",\"key\":\"person\",\"value\":{\"name\":\"Elon Musk\",\"car\":{\"model\":\"Tesla Roadster\",\"year\":\"2018\"},\"rocket\":{\"name\":\"Falcon 9\",\"launches\":\"87\"}}}"
//    val input1 = "{\"type\":\"set\",\"key\":[\"person\",\"rocket\",\"launches\"],\"value\":\"88\"}"
//    println("input:")
//    println(input1)
//
//    val map = jsonParser.jsonToMap(input1)
//    println("map:")
//    println(map)
//
//    val json = jsonParser.mapToJson(map)
//    println("json:")
//    println(json)
//
//    val map1 = jsonParser.jsonToMap(json)
//    println("map1:")
//    println(map1)
//
//    val json1 = jsonParser.mapToJson(map1)
//    println("json1:")
//    println(json1)
//
//
//    val keys = map["key"]
//    if (keys is Collection<*>)
//        keys.forEach { println(it) }
//    else
//        println(keys)
}
