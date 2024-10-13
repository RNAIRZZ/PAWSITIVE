plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.animalhealthcarefinalyearmajorproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.animalhealthcarefinalyearmajorproject"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
//    configuration.all {
//        resolutionStrategy {
//            eachDependency {
//                if ((requested.group == "org.jetbrains.kotlin") && (requested.name.startsWith("kotlin.stdlib"))){
//                    useVersion("1.8.22")
//                }
//            }
//        }
//    }
}
dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
//    implementation("androidx.compose.material:material-desktop:1.6.8")
    implementation("androidx.activity:activity:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.google.android.material:material:1.12.0")
    implementation(libs.firebase.database)
    implementation(libs.firebase.auth)
    testImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.compose.runtime:runtime-android:1.6.8")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("com.android.support:design:28.0.0")
    implementation("androidx.cardview:cardview:1.0.0")
//    implementation("com.android.support:cardview-v7:28.0.0")
//    dependencies.implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.21")
    implementation(libs.kotlin.stdlib);
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.android.volley:volley:1.2.1")



}