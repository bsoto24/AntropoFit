package pe.speira.antropometria.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import pe.speira.antropometria.room.entities.GrupoEtiquetaEntity

@Dao
interface EtiquetaDAO {

    @Query("SELECT * FROM etiqueta_table WHERE id = :grupoId")
    fun obtenerGrupoEtiquetas(grupoId: Int): LiveData<GrupoEtiquetaEntity>

}