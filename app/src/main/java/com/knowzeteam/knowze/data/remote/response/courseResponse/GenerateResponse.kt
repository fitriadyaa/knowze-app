package com.knowzeteam.knowze.data.remote.response.courseResponse

import com.google.gson.annotations.SerializedName

data class GenerateResponse(

	@field:SerializedName("course_id")
	var courseId: String? = "",

	@field:SerializedName("message")
	var message: String? = ""
)
