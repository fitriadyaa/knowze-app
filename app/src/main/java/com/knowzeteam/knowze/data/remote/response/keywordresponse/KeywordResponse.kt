package com.knowzeteam.knowze.data.remote.response.keywordresponse

import com.google.gson.annotations.SerializedName

data class KeywordResponse(
	@field:SerializedName("keywords")
	var keywords: List<String?>? = null
)
