package com.uebung_basics.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uebung_basics.data.database.model.DrawnCard

@Database(entities = [DrawnCard::class], version = 1)
abstract class DrawnCardDataBase : RoomDatabase() {
    abstract fun drawnCardDao(): DrawnCardDao
}