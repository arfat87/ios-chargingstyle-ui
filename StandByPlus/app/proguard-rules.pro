# Proguard rules for StandBy+
-keepclassmembers class * extends androidx.room.RoomDatabase {
    *;
}
-keep class com.standbyplus.data.local.entity.** { *; }
-keepattributes *Annotation*
