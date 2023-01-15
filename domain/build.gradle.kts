plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
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
    namespace = "com.app.domain"
}

dependencies {
    implementation(project(mapOf("path" to ":common")))

    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.appcompat:appcompat:1.5.1")
    implementation ("com.google.android.material:material:1.7.0")
    implementation ("javax.inject:javax.inject:1")

    // paging
    implementation ("androidx.paging:paging-runtime:3.1.1")
    implementation ("androidx.paging:paging-common:3.1.1")
}
