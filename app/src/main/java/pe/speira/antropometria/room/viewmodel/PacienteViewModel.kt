package pe.speira.antropometria.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import pe.speira.antropometria.room.entities.PacienteEntity
import pe.speira.antropometria.room.repository.PacienteRepository

class PacienteViewModel(application: Application) : AndroidViewModel(application) {

    val dni: MutableLiveData<String> = MutableLiveData()

    private val repository = PacienteRepository(application)

    fun obtenerPacientes(grupoId: Int) = repository.obtenerPacientes(grupoId)

    fun registrarPaciente(pacienteEntity: PacienteEntity) = repository.registrarPaciente(pacienteEntity)

    fun search(dni: String) {
        this.dni.value = dni
    }

}