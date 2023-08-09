package jsondatabase.db

import kotlinx.serialization.Serializable

@Serializable
data class Cell(val key: String, var value: String)
