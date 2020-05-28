package pe.speira.antropometria.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pe.speira.antropometria.room.entities.GrupoEntity
import pe.speira.antropometria.repository.GrupoRepository

class GrupoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GrupoRepository(application)

    fun obtenerGrupos() = repository.obtenerGrupos()

    fun crearGrupo(grupoEntity: GrupoEntity) {
        viewModelScope.launch {
            repository.crearGrupo(grupoEntity)
        }
    }

}