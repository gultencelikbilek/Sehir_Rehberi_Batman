package com.yaman.belediyehizmet.feature.profile

import kotlinx.serialization.Serializable

@Serializable
data class ThemeAppSettings(
    val isDarkTheme: Boolean = false
)

enum class Theme(){
    DARKTHEME,LIGHTTHEME
}