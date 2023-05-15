package com.example.singlearchitecture.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.singlearchitecture.data.local.model.UserModel

@Database(entities = [UserModel::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun daoDatabase(): Dao
}
