package com.sample.simpsonsviewer.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RelatedTopic(@SerializedName("Result") val result: String? = null, @SerializedName("Text") val text: String? = null, @SerializedName("Icon") val icon: Icon? = null): Serializable {

    fun getCharacterName(): String {
        return text?.let {
            it.split(" - ")[0]
        } ?: run { "" }
    }

}
data class Icon(@SerializedName("URL") val url: String? = null): Serializable
