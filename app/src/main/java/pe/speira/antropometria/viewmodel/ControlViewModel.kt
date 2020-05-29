package pe.speira.antropometria.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pe.speira.antropometria.room.entities.ControlEntity
import pe.speira.antropometria.repository.ControlRepository

class ControlViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ControlRepository(application)

    fun obtenerControles(dni: String) = repository.obtenerControles(dni)

    fun obtenerPacienteControl(dni: String) = repository.obtenerPacientesControles(dni)

    fun registrarControl(controlEntity: ControlEntity) {
        viewModelScope.launch {
            repository.registrarControl(controlEntity)
        }
    }

    fun eliminarControl(controlEntity: ControlEntity) {
        viewModelScope.launch {
            repository.eliminarControl(controlEntity)
        }
    }

}