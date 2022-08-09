package Dependancies

object ProjectDependancies {
    const val GRADLE = "com.android.tools.build:gradle:${DependanciesVersions.BUILD_GRAGLE_VERSION}"
    const val  KOTLIN_PLUGIN= "org.jetbrains.kotlin:kotlin-gradle-plugin:${DependanciesVersions.KOTLIN_PLUGIN_VERSION}"
    const val  HILT= "com.google.dagger:hilt-android-gradle-plugin:${DependanciesVersions.HILT_PLUGIN_VERSION}"
}