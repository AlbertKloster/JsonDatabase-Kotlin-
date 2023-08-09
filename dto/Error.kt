package jsondatabase.dto

import kotlinx.serialization.Serializable

@Serializable
data class Error(val response: String, val reason: String)
