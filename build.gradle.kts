// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    //alias(libs.plugins.org.jetbrains.kotlin.kapt) apply false
    alias(libs.plugins.hiltInject) apply false
    alias(libs.plugins.gmsGoogleService)  apply false
    alias(libs.plugins.serialization)
}