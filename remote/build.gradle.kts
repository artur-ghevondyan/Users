plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
}

val appCompileSdk: Int by rootProject.extra
val appMinSdk: Int by rootProject.extra
val appTargetSdk: Int by rootProject.extra

android {
    compileSdk = appCompileSdk

    defaultConfig {
        minSdk = appMinSdk
        targetSdk = appTargetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles.add(File("consumer-rules.pro"))
    }

    buildTypes {
        debug {
            val debugUrl: String by project
            buildConfigField("String", "apiUrl", debugUrl)

            val appVersionCode: Int by rootProject.extra
            buildConfigField("int", "appVersionCode", appVersionCode.toString())
        }
        release {
            val releaseUrl: String by project
            buildConfigField("String", "apiUrl", releaseUrl)

            val appVersionCode: Int by rootProject.extra
            buildConfigField("int", "appVersionCode", appVersionCode.toString())

            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "$project.rootDir/tools/proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    namespace = "com.app.remote"
}

dependencies {
    implementation(project(mapOf("path" to ":common")))
    implementation(project(mapOf("path" to ":data")))

    implementation ("javax.inject:javax.inject:1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")

}
