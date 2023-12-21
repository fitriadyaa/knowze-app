package com.knowzeteam.knowze.data.remote.response.videoresponse

import com.google.gson.annotations.SerializedName

data class VideoResponse(

	@field:SerializedName("videos")
	var videos: List<VideosItem?>? = null
)

data class VideosItem(

	@field:SerializedName("channel")
	var channel: String? = null,

	@field:SerializedName("link")
	var link: String? = null,

	@field:SerializedName("id")
	var id: String? = null,

	@field:SerializedName("title")
	var title: String? = null
)
