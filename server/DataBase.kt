package jsondatabase.server

class DataBase private constructor() {
    private val cells = mutableListOf<Cell>()
    companion object {
        @Volatile
        private var instance: DataBase? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: DataBase().also { instance = it }
            }
    }

    init {
        for (i in 1..100) {
            cells.add(Cell(i.toLong(), ""))
        }
    }

    fun getCellById(id: Long): Cell? {
        return cells.find { it.id == id }
    }

}