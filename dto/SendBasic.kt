package jsondatabase.dto

import kotlinx.serialization.Serializable

@Serializable
data class SendBasic(val type: String, val key: String = "0", val value: String = "")
