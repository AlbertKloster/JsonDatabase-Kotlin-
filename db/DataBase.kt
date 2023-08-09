package jsondatabase.db

import java.io.File
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.concurrent.locks.ReentrantReadWriteLock

class DataBase {
    private val fileDb = File("\\client\\data\\db.json")
    private val cells = mutableListOf<Cell>()
    private val readLock = ReentrantReadWriteLock().readLock()
    private val writeLock = ReentrantReadWriteLock().writeLock()

    init {
        readLock.lock()
        if (fileDb.exists()) {
            fileDb.forEachLine {
                if (it.isNotBlank()) {
                    try {
                        cells.add(Json.decodeFromString(it))
                    } catch (_: RuntimeException) {

                    }
                }
            }
        }
        readLock.unlock()
    }

    fun setValueByKey(key: String, value: String) {
        val cell = cells.find { it.key == key }
        if (cell != null) cell.value = value
        else cells.add(Cell(key, value))
        saveDb()
    }

    fun getValueByKey(key: String) = getCellByKey(key)?.value

    fun containsKey(key: String) = cells.any { it.key == key }

    fun removeByKey(key: String) {
        cells.remove(getCellByKey(key))
        saveDb()
    }

    private fun getCellByKey(key: String) = cells.find { it.key == key }

    private fun saveDb() {
        writeLock.lock()
        fileDb.delete()
        fileDb.createNewFile()
        cells.forEach {
            fileDb.appendText(Json.encodeToString(it))
            fileDb.appendText("\n")
        }
        writeLock.unlock()
    }

}