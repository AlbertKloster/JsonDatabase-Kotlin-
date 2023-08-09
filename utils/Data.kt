package jsondatabase.utils

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable

@Serializable
data class Data(val type: String, @EncodeDefault val key: String = "", @EncodeDefault val value: String = "")
