package com.knowzeteam.knowze.data.local

import com.knowzeteam.knowze.data.remote.response.courseResponse.CourseResponse

data class AboutContentData(
    var id: String? = null,
    val duration: String? = null,
    val typeActivity: String? = null,
    val title: String? = null,
    val themeActivity: String? = null,
    val desc: String? = null,
    val lessons: Int? = null
) {
    constructor(data: CourseResponse) : this(
        id = data.id,
        duration = data.duration,
        typeActivity = data.typeActivity,
        title = data.title,
        themeActivity = data.themeActivity,
        desc = data.desc,
        lessons = data.lessons
    )
}