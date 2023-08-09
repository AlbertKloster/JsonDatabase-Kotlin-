package jsondatabase.db

class DataBase private constructor() {
    private val cells = mutableMapOf<String, String>()
    companion object {
        @Volatile
        private var instance: DataBase? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: DataBase().also { instance = it }
            }
    }

    fun setValueByKey(key: String, value: String) {
        cells[key] = value
    }

    fun getValueByKey(key: String) = cells[key]

    fun containsKey(key: String) = cells.containsKey(key)

    fun remove(key: String) = cells.remove(key)

}