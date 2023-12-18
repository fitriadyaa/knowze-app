package com.knowzeteam.knowze.data.remote.response.newsresponse

import com.google.gson.annotations.SerializedName

data class NewsResponse(

	@field:SerializedName("NewsResponse")
	val newsResponse: List<NewsResponseItem?>? = null
)

data class NewsResponseItem(

	@field:SerializedName("img_url")
	val imgUrl: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null
)
