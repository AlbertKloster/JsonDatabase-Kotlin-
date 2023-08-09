package jsondatabase.dto

import kotlinx.serialization.Serializable

@Serializable
data class ResponseGet(val response: String, val value: String)
