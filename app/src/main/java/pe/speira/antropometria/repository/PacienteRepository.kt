package pe.speira.antropometria.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.withTransaction
import pe.speira.antropometria.room.AppDatabase
import pe.speira.antropometria.room.dao.ControlDAO
import pe.speira.antropometria.room.dao.PacienteDAO
import pe.speira.antropometria.room.entities.PacienteEntity

class PacienteRepository(aplication: Application) {

    var db = AppDatabase.getInstance(aplication)
    var pacienteDAO: PacienteDAO? = db?.pacienteDAO()
    var controlDAO: ControlDAO? = db?.controlDAO()

    fun obtenerPacientes(grupoId: Int): LiveData<List<PacienteEntity>> =
        pacienteDAO?.obtenerPacientes(grupoId) ?: MutableLiveData()

    suspend fun registrarPaciente(pacienteEntity: PacienteEntity) {
        pacienteDAO?.registrarPaciente(pacienteEntity)
    }

    suspend fun eliminarPaciente(pacienteEntity: PacienteEntity) {
        db?.withTransaction {
            controlDAO?.eliminarControles(pacienteEntity.dni)
            pacienteDAO?.eliminarPaciente(pacienteEntity)
        }
    }

}