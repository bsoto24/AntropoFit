package pe.speira.antropometria.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import pe.speira.antropometria.room.entities.ControlEntity

@Dao
interface ControlDAO {

    @Query("SELECT * FROM control_table WHERE pacienteDni = :dni  ORDER BY id DESC")
    fun obtenerControles(dni: String): LiveData<List<ControlEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registrarControl(controlEntity: ControlEntity)

    @Delete
    suspend fun eliminarControl(controlEntity: ControlEntity)

}