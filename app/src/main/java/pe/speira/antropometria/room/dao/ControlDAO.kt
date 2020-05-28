package pe.speira.antropometria.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pe.speira.antropometria.room.entities.ControlEntity

@Dao
interface ControlDAO {

    @Query("SELECT * FROM control_table WHERE paciente_dni = :dni  ORDER BY control_id DESC")
    fun obtenerControles(dni: String): LiveData<List<ControlEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registrarControl(controlEntity: ControlEntity)

}