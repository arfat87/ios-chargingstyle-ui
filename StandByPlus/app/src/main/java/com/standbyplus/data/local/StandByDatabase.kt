package com.standbyplus.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.standbyplus.data.local.dao.LayoutDao
import com.standbyplus.data.local.dao.ThemeDao
import com.standbyplus.data.local.entity.ClockThemeEntity
import com.standbyplus.data.local.entity.SavedLayoutEntity

@Database(
    entities = [SavedLayoutEntity::class, ClockThemeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StandByDatabase : RoomDatabase() {
    abstract fun layoutDao(): LayoutDao
    abstract fun themeDao(): ThemeDao

    companion object {
        @Volatile
        private var INSTANCE: StandByDatabase? = null

        fun getInstance(context: Context): StandByDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StandByDatabase::class.java,
                    "standby_plus.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
