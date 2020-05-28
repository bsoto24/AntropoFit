package pe.speira.antropometria.room.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pe.speira.antropometria.room.AppDatabase
import pe.speira.antropometria.room.dao.PacienteDAO
import pe.speira.antropometria.room.entities.PacienteEntity

class PacienteRepository(aplication: Application) {

    var pacienteDAO: PacienteDAO? = AppDatabase.getInstance(aplication)?.pacienteDAO()

    fun obtenerPacientes(grupoId: Int): LiveData<List<PacienteEntity>> =
        pacienteDAO?.obtenerPacientes(grupoId) ?: MutableLiveData()

    suspend fun registrarPaciente(pacienteEntity: PacienteEntity) {
        pacienteDAO?.registrarPaciente(pacienteEntity)
    }

}