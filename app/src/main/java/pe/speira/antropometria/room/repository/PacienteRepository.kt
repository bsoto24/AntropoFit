package pe.speira.antropometria.room.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pe.speira.antropometria.room.entities.PacienteEntity
import pe.speira.antropometria.room.dao.PacienteDAO
import pe.speira.antropometria.room.AppDatabase

class PacienteRepository(aplication: Application) {

    var pacienteDAO: PacienteDAO? = AppDatabase.getInstance(aplication)?.pacienteDAO()

    fun obtenerPacientes(grupoId: Int): LiveData<List<PacienteEntity>> =
        pacienteDAO?.obtenerPacientes(grupoId) ?: MutableLiveData()

    fun registrarPaciente(pacienteEntity: PacienteEntity) =
        pacienteDAO?.let { InsertAsyncTask(it).execute(pacienteEntity) }

    private class InsertAsyncTask(private val pacienteDAO: PacienteDAO) :
        AsyncTask<PacienteEntity, Void, Void>() {
        override fun doInBackground(vararg pacientes: PacienteEntity?): Void? {
            for (paciente in pacientes) {
                if (paciente != null) pacienteDAO.registrarPaciente(paciente)
            }
            return null
        }
    }

}