package com.knowzeteam.knowze.data.remote.response.courseResponse

import com.google.gson.annotations.SerializedName

data class AllCourseResponse(

	@field:SerializedName("next")
	val next: Any? = null,

	@field:SerializedName("previous")
	val previous: Any? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsCourseItem?>? = null
)

data class ResultsCourseItem(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null
)
