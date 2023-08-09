package jsondatabase.dto

import kotlinx.serialization.Serializable

@Serializable
data class SendGet(val type: String, val key: String)
