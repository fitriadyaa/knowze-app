package com.knowzeteam.knowze.data.remote.response

import com.google.gson.annotations.SerializedName

data class DashboardResponse(

    @field:SerializedName("courses")
    val courses: List<CoursesItem?>? = null,

    @field:SerializedName("img")
    val img: String? = null,

    @field:SerializedName("course_progress")
    val courseProgress: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("progress")
    val progress: List<ProgressItem?>? = null,

    @field:SerializedName("course_completed")
    val courseCompleted: Int? = null
)

data class CoursesItem(

    @field:SerializedName("duration")
    val duration: String? = null,

    @field:SerializedName("is_done")
    val isDone: Boolean? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("total_lessons")
    val totalLessons: Int? = null
)

data class ProgressItem(

    @field:SerializedName("duration")
    val duration: String? = null,

    @field:SerializedName("is_done")
    val isDone: Boolean? = null,

    @field:SerializedName("progress")
    val progress: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null
)
