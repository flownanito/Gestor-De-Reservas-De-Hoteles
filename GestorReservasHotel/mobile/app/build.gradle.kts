plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.proyect.reservationmanager"
    compileSdk = 34 // He ajustado esto a 34 o 35, release(36) suele ser beta y dar problemas

    defaultConfig {
        applicationId = "com.proyect.reservationmanager"
        minSdk = 24
        targetSdk = 34
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
    // Dependencias básicas del catálogo
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Testing básico
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // --- DEPENDENCIAS FUSIONADAS ---

    // UI Extras (CardView es vital para el diseño de Perfil)
    implementation("androidx.cardview:cardview:1.0.0")

    // Retrofit (Cliente HTTP)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Interceptor (Muy útil para ver en el Logcat qué datos viajan)
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Gson Explicito
    implementation("com.google.code.gson:gson:2.10.1")
}