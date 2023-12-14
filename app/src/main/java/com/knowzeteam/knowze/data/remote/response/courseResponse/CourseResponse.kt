package com.knowzeteam.knowze.data.remote.response.courseResponse

data class CourseResponse(
	val duration: String? = null,
	val subtitles: List<SubtitlesItem?>? = null,
	val title: String? = null,
	val desc: String? = null,
	val lessons: Int? = null
)

data class Content(
	val closing: String? = null,
	val id: String? = null,
	val opening: String? = null,
	val steps: List<String?>? = null
)

data class SubtitlesItem(
	val isDone: Boolean? = null,
	val shortdesc: String? = null,
	val topic: String? = null,
	val id: String? = null,
	val content: Content? = null
)

