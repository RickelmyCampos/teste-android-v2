import java.io.FileInputStream
import java.util.Properties

plugins {
    id("kotlin-kapt")
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    kotlin("plugin.serialization") version "1.9.23"

    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.gilbersoncampos.testeaiko"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gilbersoncampos.testeaiko"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isDebuggable=false
            isJniDebuggable=false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            val secretPropertiesFile = rootProject.file("secrets.properties")
            val secretProperties = Properties()
            secretProperties.load(FileInputStream(secretPropertiesFile))
            buildConfigField(
                "String",
                "URL",
                secretProperties["URL"] as String
            )
            buildConfigField(
                "String",
                "TOKEN",
                secretProperties["TOKEN"] as String
            )
            buildConfigField(
                "String",
                "API_KEY_MAPS",
                secretProperties["API_KEY_MAPS"] as String
            )
        }
        debug {
            isDebuggable=true
            isJniDebuggable=true
            val secretPropertiesFile = rootProject.file("secrets.properties")
            val secretProperties = Properties()
            secretProperties.load(FileInputStream(secretPropertiesFile))
            buildConfigField(
                "String",
                "URL",
                secretProperties["URL"] as String
            )
            buildConfigField(
                "String",
                "TOKEN",
                secretProperties["TOKEN"] as String
            )
            buildConfigField(
                "String",
                "API_KEY_MAPS",
                secretProperties["API_KEY_MAPS"] as String
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    secrets{
        propertiesFileName="secrets.properties"
        defaultPropertiesFileName = "local.defaults.properties"
        ignoreList.add("keyToIgnore")
        ignoreList.add("sdk.*")
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    //serialization
    implementation(libs.kotlinx.serialization.json)
    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    //maps
    implementation(libs.play.services.maps)
    implementation(libs.maps.ktx)
    implementation(libs.maps.utils.ktx)
    //compose maps library
    implementation(libs.maps.compose)
    implementation(libs.maps.compose.utils)
    implementation(libs.maps.compose.widgets)
    //compose navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
kapt{
    correctErrorTypes = true
}