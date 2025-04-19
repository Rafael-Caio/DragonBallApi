plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.safe.args)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.rafaeltech.apidragonball"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.rafaeltech.apidragonball"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packaging {
        resources {
            excludes += mutableSetOf(
                "META-INF/AL2.0",
                "META-INF/LGPL2.1",
                "mozilla/public-suffix-list.txt",
                "kotlin/coroutines/coroutines.kotlin_builtins"
            )
        }
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    //Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.analytics)
    //Shimmer
    implementation(libs.shimmer)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.constraintlayout)

    //Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.kotlinx.coroutines.android)

    //Retrofit, Okhttp, Glide, JsonCoverter, Interceptor
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation (libs.glide)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    implementation(libs.androidx.fragment.ktx)
}