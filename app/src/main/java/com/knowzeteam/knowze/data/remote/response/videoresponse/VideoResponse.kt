package com.knowzeteam.knowze.data.remote.response.videoresponse

import com.google.gson.annotations.SerializedName

data class VideoResponse(

	@field:SerializedName("videos")
	val videos: List<VideosItem?>? = null
)

data class VideosItem(

	@field:SerializedName("channel")
	val channel: String? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)
