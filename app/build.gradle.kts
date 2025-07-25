plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.mvpdome"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mvpdome"
        minSdk = 20
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
//        viewBinding = true
        buildConfig = true
    }
    buildTypes {
        release {
            buildConfigField("String", "BASE_URL", "\"http://api.example.com/release\"")
            buildConfigField("boolean", "DEBUG", "false")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            // 自定义 BuildConfig 常量
            buildConfigField("String", "BASE_URL", "\"http://api.example.com/debug\"")
            buildConfigField("boolean", "DEBUG", "true")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
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
    //日志拦截器
    implementation(libs.loggingInterceptor)
    implementation(libs.adapterRxjava)
    implementation(libs.gson)
    //rxjava
    implementation(libs.rxandroid)
    implementation(libs.rxjava)
    implementation(libs.preference)

    implementation(libs.glide)
}