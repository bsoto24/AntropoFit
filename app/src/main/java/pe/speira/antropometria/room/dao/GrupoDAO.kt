package pe.speira.antropometria.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pe.speira.antropometria.room.entities.GrupoEntity

@Dao
interface GrupoDAO {

    @Query("SELECT * FROM grupo_table ORDER BY grupo_id DESC")
    fun obtenerGrupos(): LiveData<List<GrupoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun crearGrupo(grupoEntity: GrupoEntity)

}