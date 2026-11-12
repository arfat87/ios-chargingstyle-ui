package com.standbyplus.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_layouts")
data class SavedLayoutEntity(
    @PrimaryKey val id: String,
    val name: String,
    val clockStyle: String,
    val leftWidget: String,
    val rightWidget: String,
    val is24Hour: Boolean,
    val showSeconds: Boolean,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "clock_themes")
data class ClockThemeEntity(
    @PrimaryKey val id: String,
    val name: String,
    val primaryColorHex: String,
    val secondaryColorHex: String,
    val isCustom: Boolean = false
)
