package com.knowzeteam.knowze.data.remote.retrofit

import com.knowzeteam.knowze.data.remote.response.courseResponse.AllCourseResponse
import com.knowzeteam.knowze.data.remote.response.courseResponse.CourseResponse
import com.knowzeteam.knowze.data.remote.response.courseResponse.GenerateRequest
import com.knowzeteam.knowze.data.remote.response.courseResponse.GenerateResponse
import com.knowzeteam.knowze.data.remote.response.dashboard.DashboardResponse
import com.knowzeteam.knowze.data.remote.response.keywordresponse.KeywordResponse
import com.knowzeteam.knowze.data.remote.response.newsresponse.NewsResponse
import com.knowzeteam.knowze.data.remote.response.newsresponse.NewsResponseItem
import com.knowzeteam.knowze.data.remote.response.recommendationresponse.RecommendationResponse
import com.knowzeteam.knowze.data.remote.response.videoresponse.VideoRequest
import com.knowzeteam.knowze.data.remote.response.videoresponse.VideoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/api/dashboard/")
    suspend fun getDashboardData(@Header("Authorization") idToken: String): Response<DashboardResponse>

    @POST("/api/generate/")
    suspend fun postGenerate(
        @Header("Authorization") idToken: String,
        @Body generateRequest: GenerateRequest
    ): Response<GenerateResponse>

    @GET("/api/course/{course_id}")
    suspend fun getCourseDetails(
        @Header("Authorization") idToken: String,
        @Path("course_id") courseId: String
    ): Response<CourseResponse>

    @GET("/api/keyword-trending")
    suspend fun getKeywordTrending(): Response<KeywordResponse>

    @GET("/api/recommendation")
    suspend fun getRecommendation(
        @Header("Authorization") idToken: String,
    ): Response<RecommendationResponse>

    @GET("/api/course")
    suspend fun getAllCourse(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Response<AllCourseResponse>

    @GET("/api/trending-news")
    suspend fun getNews(
        @Header("Authorization") idToken: String,
    ): Response<List<NewsResponseItem>>

    @POST("/api/video/")
    suspend fun getVideo(
        @Header("Authorization") idToken: String,
        @Body videoRequest: VideoRequest
    ): Response<VideoResponse>
}
