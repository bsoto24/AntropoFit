package pe.speira.antropometria.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pe.speira.antropometria.room.entities.ControlEntity
import pe.speira.antropometria.room.repository.ControlRepository

class ControlViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ControlRepository(application)

    fun obtenerControles(dni: String) = repository.obtenerControles(dni)

    fun registrarControl(controlEntity: ControlEntity) = repository.registrarControl(controlEntity)

}