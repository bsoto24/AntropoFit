package pe.speira.antropometria.room.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pe.speira.antropometria.room.entities.ControlEntity
import pe.speira.antropometria.room.AppDatabase
import pe.speira.antropometria.room.dao.ControlDAO

class ControlRepository(aplication: Application) {

    var pacienteDAO: ControlDAO? = AppDatabase.getInstance(aplication)?.controlDAO()

    fun obtenerControles(dni: String): LiveData<List<ControlEntity>> =
        pacienteDAO?.obtenerControles(dni) ?: MutableLiveData()

    fun registrarControl(controlEntity: ControlEntity) {
        AsyncTask.execute {
            pacienteDAO?.registrarControl(controlEntity)
        }
    }

}