package jsondatabase.dto

import kotlinx.serialization.Serializable

@Serializable
data class SendDelete(val type: String, val key: String)
