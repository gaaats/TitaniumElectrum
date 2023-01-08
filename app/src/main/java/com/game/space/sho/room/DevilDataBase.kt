package com.game.space.sho.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DevilEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DevilDataBase() : RoomDatabase() {

    abstract fun getDevilDAO(): DevilDao

    companion object{

        private var INSTANCE: DevilDataBase? = null
        private val LOCK = Any()
        private const val DATA_BASE_NAME = "devil_.db"

        fun getDataBase(application: Application): DevilDataBase{
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK){
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    DevilDataBase::class.java,
                    DATA_BASE_NAME
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}