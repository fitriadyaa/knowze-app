package com.knowzeteam.knowze.data.remote.response

import com.google.gson.annotations.SerializedName

data class AllFeedbackResponse(

	@field:SerializedName("next")
	val next: Any? = null,

	@field:SerializedName("previous")
	val previous: Any? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsFeedbackItem?>? = null
)

data class ResultsFeedbackItem(

	@field:SerializedName("feedback")
	val feedback: List<Any?>? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)

data class FeedbackItem(

	@field:SerializedName("sentiment")
	val sentiment: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("text")
	val text: String? = null
)
