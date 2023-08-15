package jsondatabase.db

import jsondatabase.Constants
import jsondatabase.utils.Parser
import java.io.File
import java.util.concurrent.locks.ReentrantReadWriteLock

class DataBase {
    private val fileDb = File(Constants.DB_PATH + "db.json")
    private val cells = mutableMapOf<String, Any?>()
    private val readLock = ReentrantReadWriteLock().readLock()
    private val writeLock = ReentrantReadWriteLock().writeLock()
    private val parser = Parser()

    init {
        readLock.lock()
        if (fileDb.exists())
            cells.putAll(parser.jsonToMap(fileDb.readText()))
        readLock.unlock()
    }

    fun setValueForKeys(keys: List<String>, value: Any) {
        var obj: Any? = cells
        for (i in 0 until keys.size - 1) {
            obj = (obj as MutableMap<*, *>)[keys[i]]
        }
        (obj as MutableMap<String, Any>)[keys.last()] = value
        saveDb()
    }

    fun getValueForKeys(keys: Collection<String>): Any? {
        var value: Any? = cells
        for (key in keys) {
            value = (value as Map<*, *>)[key]
        }
        return value
    }

    fun deleteValueForKeys(keys: List<String>) {
        var obj: Any? = cells
        for (i in 0 until keys.size - 1) {
            obj = (obj as MutableMap<*, *>)[keys[i]]
        }
        (obj as MutableMap<String, Any>).remove(keys.last())


        saveDb()
    }

    private fun saveDb() {
        writeLock.lock()
        fileDb.delete()
        fileDb.createNewFile()
        fileDb.writeText(parser.mapToJson(cells as Map<String, Any>))
        writeLock.unlock()
    }

}