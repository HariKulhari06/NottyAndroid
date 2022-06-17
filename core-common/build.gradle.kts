plugins {
    id("notty.android.library")
    id("notty.android.library.jacoco")
    kotlin("kapt")
    id("notty.spotless")
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

   // testImplementation(project(":core-testing"))
}