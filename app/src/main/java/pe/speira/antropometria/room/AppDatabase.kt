package pe.speira.antropometria.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import pe.speira.antropometria.room.converters.Converters
import pe.speira.antropometria.room.dao.ControlDAO
import pe.speira.antropometria.room.dao.GrupoDAO
import pe.speira.antropometria.room.dao.PacienteDAO
import pe.speira.antropometria.room.entities.*

@Database(
    entities = [GrupoEntity::class, PacienteEntity::class, ControlEntity::class, EtiquetaEntity::class, DeporteEntity::class],
    version = 3,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun grupoDAO(): GrupoDAO
    abstract fun pacienteDAO(): PacienteDAO
    abstract fun controlDAO(): ControlDAO

    companion object {

        private const val DATABASE_NAME = "antropometria"

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE control_table ADD COLUMN nota TEXT NOT NULL DEFAULT ''")
                database.execSQL("UPDATE control_table SET nota = 'Descata en altura' WHERE talla > 190")
                database.execSQL("UPDATE control_table SET nota = 'Cuidado con el sobrepeso' WHERE peso > 80")
            }
        }

        private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS deporte_table (deporteId INTEGER NOT NULL, descripcion TEXT NOT NULL, PRIMARY KEY(deporteId))")
            }
        }

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).addMigrations(MIGRATION_1_2, MIGRATION_2_3).build()
            }
            return INSTANCE
        }

    }

}