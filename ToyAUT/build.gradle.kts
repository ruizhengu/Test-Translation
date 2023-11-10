buildscript {
    extra.apply {
        set("appiumKotlin", properties.getOrDefault("appiumKotlin", "1.8.10"))
        set(
            "appiumAndroidGradlePlugin",
            properties.getOrDefault("appiumAndroidGradlePlugin", "8.1.1")
        )
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}
//val sourceCompatibility by extra(VERSION_17)
