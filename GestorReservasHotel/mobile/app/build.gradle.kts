plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.proyect.reservationmanager"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.proyect.reservationmanager"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // Retrofit (El cliente HTTP)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Gson Converter (Para traducir de JSON a Java)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
}