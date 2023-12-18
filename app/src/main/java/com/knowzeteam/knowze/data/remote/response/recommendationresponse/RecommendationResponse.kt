package com.knowzeteam.knowze.data.remote.response.recommendationresponse

import com.google.gson.annotations.SerializedName

data class RecommendationResponse(
	@field:SerializedName("recommendations")
	val recommendations: List<String?>? = null
)
