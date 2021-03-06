package pe.speira.antropometria.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import pe.speira.antropometria.room.entities.ControlEntity
import pe.speira.antropometria.room.entities.PacienteControlEntity

@Dao
interface ControlDAO {

    @Query("SELECT * FROM control_table WHERE pacienteDni = :dni  ORDER BY id DESC")
    fun obtenerControles(dni: String): LiveData<List<ControlEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registrarControl(controlEntity: ControlEntity)

    @Delete
    suspend fun eliminarControl(controlEntity: ControlEntity)

    @Query("DELETE FROM control_table WHERE pacienteDni = :dni")
    suspend fun eliminarControles(dni: String)

    @Transaction @Query("SELECT * FROM paciente_table WHERE dni = :dni")
    fun obtenerPacienteControles(dni: String): LiveData<PacienteControlEntity>

}