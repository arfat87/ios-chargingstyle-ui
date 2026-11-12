package com.standbyplus.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.standbyplus.data.local.entity.ClockThemeEntity
import com.standbyplus.data.local.entity.SavedLayoutEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LayoutDao {
    @Query("SELECT * FROM saved_layouts ORDER BY createdAt DESC")
    fun getAllLayouts(): Flow<List<SavedLayoutEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLayout(layout: SavedLayoutEntity)

    @Query("DELETE FROM saved_layouts WHERE id = :layoutId")
    suspend fun deleteLayout(layoutId: String)
}

@Dao
interface ThemeDao {
    @Query("SELECT * FROM clock_themes")
    fun getAllThemes(): Flow<List<ClockThemeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTheme(theme: ClockThemeEntity)
}
