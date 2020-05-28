package pe.speira.antropometria.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pe.speira.antropometria.room.entities.PacienteEntity

@Dao
interface PacienteDAO {

    @Query("SELECT * FROM paciente_table WHERE grupoId = :grupoId  ORDER BY fecha_nacimiento DESC")
    fun obtenerPacientes(grupoId: Int): LiveData<List<PacienteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registrarPaciente(pacienteEntity: PacienteEntity)

}