package pe.speira.antropometria.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pe.speira.antropometria.room.AppDatabase
import pe.speira.antropometria.room.dao.GrupoDAO
import pe.speira.antropometria.room.entities.GrupoEntity

class GrupoRepository(aplication: Application) {

    var grupoDAO: GrupoDAO? = AppDatabase.getInstance(aplication)?.grupoDAO()

    fun obtenerGrupos(): LiveData<List<GrupoEntity>> = grupoDAO?.obtenerGrupos() ?: MutableLiveData()

    suspend fun crearGrupo(grupoEntity: GrupoEntity) {
        grupoDAO?.crearGrupo(grupoEntity)
    }

}