package pe.speira.antropometria.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pe.speira.antropometria.room.entities.ControlEntity
import pe.speira.antropometria.room.entities.GrupoEntity
import pe.speira.antropometria.room.entities.PacienteEntity
import pe.speira.antropometria.room.converters.Converters
import pe.speira.antropometria.room.dao.ControlDAO
import pe.speira.antropometria.room.dao.GrupoDAO
import pe.speira.antropometria.room.dao.PacienteDAO
import pe.speira.antropometria.room.entities.EtiquetaEntity

@Database(entities = [GrupoEntity::class, PacienteEntity::class, ControlEntity::class, EtiquetaEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun grupoDAO(): GrupoDAO
    abstract fun pacienteDAO(): PacienteDAO
    abstract fun controlDAO(): ControlDAO

    companion object {

        private const val DATABASE_NAME = "antropometria"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }

    }

}