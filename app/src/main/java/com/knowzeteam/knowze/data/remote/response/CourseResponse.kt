package com.knowzeteam.knowze.data.remote.response

import com.google.gson.annotations.SerializedName

data class CourseResponse(

	@field:SerializedName("duration")
	val duration: String? = null,

	@field:SerializedName("subtitles")
	val subtitles: List<SubtitlesItem?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null,

	@field:SerializedName("lessons")
	val lessons: Int? = null
)

data class Content(

	@field:SerializedName("closing")
	val closing: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("opening")
	val opening: String? = null,

	@field:SerializedName("steps")
	val steps: List<String?>? = null
)

data class SubtitlesItem(

	@field:SerializedName("is_done")
	val isDone: Boolean? = null,

	@field:SerializedName("shortdesc")
	val shortdesc: String? = null,

	@field:SerializedName("topic")
	val topic: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("content")
	val content: Content? = null
)
