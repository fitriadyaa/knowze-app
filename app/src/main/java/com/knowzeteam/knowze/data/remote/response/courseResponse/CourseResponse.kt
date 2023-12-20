package com.knowzeteam.knowze.data.remote.response.courseResponse

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

data class CourseResponse(

	var id: String? = null,

	@field:SerializedName("duration")
	val duration: String? = null,

	@field:SerializedName("subtitles")
	val subtitles: List<SubtitlesItem?>? = null,

	@field:SerializedName("type_activity")
	val typeActivity: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("theme_activity")
	val themeActivity: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null,

	@field:SerializedName("lessons")
	val lessons: Int? = null
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
