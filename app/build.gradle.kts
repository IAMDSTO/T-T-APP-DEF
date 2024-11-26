plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    namespace = "com.example.ttdef"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ttdef"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Dependencias de Android
    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Conector MySQL
    implementation("mysql:mysql-connector-java:8.0.30")

    // Retrofit para realizar peticiones HTTP
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Gson Converter para manejar respuestas JSON con Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp para manejar las conexiones HTTP
    implementation("com.squareup.okhttp3:okhttp:4.9.0")

    // Dependencias de prueba (puedes eliminarlas si no las usas)
    implementation("mysql:mysql-connector-java:8.0.30")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}