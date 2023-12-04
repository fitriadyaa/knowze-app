package com.knowzeteam.knowze.data.remote.response

import com.google.gson.annotations.SerializedName

data class GenerateResponse(

	@field:SerializedName("course_id")
	val courseId: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)
