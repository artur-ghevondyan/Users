buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.42")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.7.0")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = java.net.URI.create("https://jitpack.io") }
    }
}

extra["appCompileSdk"] = 32
extra["appMinSdk"] = 23
extra["appTargetSdk"] = 32
extra["appVersionCode"] = 1
extra["appVersionName"] = "1.0"

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
