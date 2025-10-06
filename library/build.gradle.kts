import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
//    id("maven-publish")
//    id("signing")
    id("com.vanniktech.maven.publish")
}

android {
    namespace = "io.github.akbardzulfikar.scalabletablayout"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }

    // 👇 AGP 8.x runs on JDK 17; compile to 11 bytecode if you want, but use toolchain 17
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    // (Optional) prefer toolchains over relying on system JDK
    // java {
    //     toolchain { languageVersion.set(JavaLanguageVersion.of(17)) }
    // }

    // 👇 tell AGP which variant(s) to publish & attach sources/javadoc
//    publishing {
//        singleVariant("release") {
//            withSourcesJar()
//            withJavadocJar()
//        }
//    }
}

group = findProperty("POM_GROUP_ID") as String
version = findProperty("POM_VERSION") as String

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // 👇 expose Material to consumers
    api(libs.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

//mavenPublishing {
//    // target Central
////    publishToMavenCentral()  // << penting
//    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
//
////    signAllPublications()
//
//    coordinates(
//        findProperty("POM_GROUP_ID") as String,
//        findProperty("POM_ARTIFACT_ID") as String,
//        findProperty("POM_VERSION") as String
//    )
//
//    pom {
//        name.set(findProperty("POM_NAME") as String)
//        description.set(findProperty("POM_DESCRIPTION") as String)
//        url.set(findProperty("POM_URL") as String)
//
//        licenses {
//            license {
//                name.set(findProperty("POM_LICENSE_NAME") as String)
//                url.set(findProperty("POM_LICENSE_URL") as String)
//            }
//        }
//        developers {
//            developer {
//                id.set(findProperty("POM_DEVELOPER_ID") as String)
//                name.set(findProperty("POM_DEVELOPER_NAME") as String)
//            }
//        }
//        scm {
//            url.set(findProperty("POM_SCM_URL") as String)
//            connection.set(findProperty("POM_SCM_CONNECTION") as String)
//            developerConnection.set(findProperty("POM_SCM_DEV_CONNECTION") as String)
//        }
//    }
//}
