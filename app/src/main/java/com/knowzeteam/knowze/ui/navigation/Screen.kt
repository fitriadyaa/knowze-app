package com.knowzeteam.knowze.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Onboard1 : Screen("onboarding_one")
    object Onboard2: Screen("onboarding_second")
    object Onboard3: Screen("onboarding_thrid")
    object Login: Screen("login_screen")
    object Home : Screen("home_screen")
    object EmailLogin : Screen("email_login_screen")
    object Register : Screen("register_screen")
    object AboutCourse : Screen("about_course")
    object AboutContent : Screen("about_content")
    object DetailCourse : Screen("detail_course")
    object TrendingKeyword : Screen("trending_keyword")
    object HomeS : Screen("home_search")
    object GeneratingScreen : Screen("generating_course_screen")
    object CourseGallery : Screen("course_gallery")
    object VideoPlayerScreen: Screen("video_player_screen")
    object Youtube: Screen("youtube_screen")
}