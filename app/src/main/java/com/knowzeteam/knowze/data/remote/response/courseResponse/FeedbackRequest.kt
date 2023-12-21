package com.knowzeteam.knowze.data.remote.response.courseResponse

import com.google.gson.annotations.SerializedName

data class FeedbackRequest(

	@field:SerializedName("rating")
	val rating: Int? = null,

	@field:SerializedName("text")
	val text: String? = null
)
