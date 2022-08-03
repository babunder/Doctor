package demo.com.mydoctors.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Disease::class], version = 1, exportSchema = false)
abstract class DiseaseRoomDatabase : RoomDatabase() {

    abstract fun diseaseDAO(): DiseaseDAO

    companion object {

        @Volatile
        private var INSTANCE: DiseaseRoomDatabase? = null

        @Synchronized
        fun getDatabase(context: Context, scope: CoroutineScope): DiseaseRoomDatabase {

            if (INSTANCE == null) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DiseaseRoomDatabase::class.java,
                    "Doctor_Database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
            }
            return INSTANCE!!
        }
    }

}