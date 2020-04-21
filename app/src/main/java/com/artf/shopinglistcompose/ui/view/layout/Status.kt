package com.artf.shopinglistcompose.ui.view.layout

import androidx.compose.Model
import androidx.compose.frames.ModelList

/**
 * Class defining the screens we have in the app: home, article details and interests
 */
sealed class Screen {
    object Home : Screen()
    data class Article(val postId: String) : Screen()
    object Interests : Screen()
}

@Model
object AppStatus {
    var currentScreen: Screen = Screen.Home
    val favorites = ModelList<String>()
    val selectedTopics = ModelList<String>()
}

/**
 * Temporary solution pending navigation support.
 */
fun navigateTo(destination: Screen) {
    AppStatus.currentScreen = destination
}
