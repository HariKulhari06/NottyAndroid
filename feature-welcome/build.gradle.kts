plugins {
    id("notty.android.library")
    id("notty.android.feature")
    id("notty.android.library.compose")
    id("notty.android.library.jacoco")
    id("dagger.hilt.android.plugin")
    id("notty.spotless")
}

dependencies {
    implementation(libs.kotlinx.datetime)

    implementation(libs.androidx.compose.material3.windowSizeClass)

    implementation(libs.accompanist.flowlayout)
}
