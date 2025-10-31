plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.yourname.freetranscriber"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.yourname.freetranscriber"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        // Load optional ChatGPT key
        val chatKey = project.properties["CHATGPT_API_KEY"]?.toString() ?: ""
        buildConfigField("String", "CHATGPT_API_KEY", "\"$chatKey\"")
    }

    externalNativeBuild { cmake { path("src/main/cpp/CMakeLists.txt") } }

    buildFeatures { compose = true; buildConfig = true }
    composeOptions { kotlinCompilerExtensionVersion = "1.5.14" }
}

dependencies {
    // Core
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation(platform("androidx.compose:compose-bom:2024.09.03"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.3.0")
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    ksp("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Room
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    // PDF export
    implementation("com.mohamedrejeb.calf:calf-pdf:0.3.0")

    // Permissions
    implementation("com.google.accompanist:accompanist-permissions:0.35.0-alpha")

    // OkHttp (ChatGPT)
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
}
