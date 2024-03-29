package com.nandaiqbalh.sahabatmovie.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nandaiqbalh.sahabatmovie.data.local.database.user.UserDao
import com.nandaiqbalh.sahabatmovie.data.local.model.user.UserEntity
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private const val DB_NAME = "sahabatMovie.db"

        @Volatile
        var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    val passphrase: ByteArray =
                        SQLiteDatabase.getBytes("sahabatMovie-hashed".toCharArray())
                    val factory = SupportFactory(passphrase)

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .openHelperFactory(factory)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}