package jsondatabase.dto

import kotlinx.serialization.Serializable

@Serializable
data class SendSet(val type: String, val key: String, val value: String)
