
// TODO: Remove once https://youtrack.jetbrains.com/issue/KTIJ-19369 is fixed
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("notty.android.library")
    id("notty.android.library.jacoco")
    id("kotlinx-serialization")
    alias(libs.plugins.ksp)
    id("notty.spotless")
}

dependencies {
    // testImplementation(project(":core-testing"))

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
}