package com.knowzeteam.knowze.data.remote.response.courseResponse

import com.google.gson.annotations.SerializedName

data class GenerateRequest(
    @field:SerializedName("prompt")
    val prompt: String
)
