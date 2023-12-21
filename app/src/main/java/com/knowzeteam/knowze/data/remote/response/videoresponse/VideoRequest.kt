package com.knowzeteam.knowze.data.remote.response.videoresponse

import com.google.gson.annotations.SerializedName

data class VideoRequest(

	@field:SerializedName("prompt")
	val prompt: String? = null
)
