package pe.speira.antropometria.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pe.speira.antropometria.room.entities.PacienteEntity
import pe.speira.antropometria.room.repository.PacienteRepository

class PacienteViewModel(application: Application) : AndroidViewModel(application) {

    val dni: MutableLiveData<String> = MutableLiveData()

    private val repository = PacienteRepository(application)

    fun obtenerPacientes(grupoId: Int) = repository.obtenerPacientes(grupoId)

    fun registrarPaciente(pacienteEntity: PacienteEntity) {
        viewModelScope.launch {
            repository.registrarPaciente(pacienteEntity)
        }
    }

}