buildscript {
    dependencies {
        classpath(libs.google.services)
    }
}
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
}
val storePassword by extra("12345678")
val storeLocation by extra("/Users/laniina/FullStack/Pherus/key/metospherus.jks")
val keyAlias by extra("metospherus")
val keyPassword by extra("12345678")
true