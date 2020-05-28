package pe.speira.antropometria.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pe.speira.antropometria.room.entities.GrupoEntity
import pe.speira.antropometria.room.repository.GrupoRepository

class GrupoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GrupoRepository(application)

    fun obtenerGrupos() = repository.obtenerGrupos()

    fun crearGrupo(grupoEntity: GrupoEntity) {
        repository.crearGrupo(grupoEntity)
    }

}