package com.sample.simpsonsviewer.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetCharactersResponse(@SerializedName("RelatedTopics") val characters: List<RelatedTopic>? = null): Serializable