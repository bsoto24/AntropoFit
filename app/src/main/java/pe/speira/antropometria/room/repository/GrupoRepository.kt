package pe.speira.antropometria.room.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pe.speira.antropometria.room.entities.GrupoEntity
import pe.speira.antropometria.room.dao.GrupoDAO
import pe.speira.antropometria.room.AppDatabase

class GrupoRepository(aplication: Application) {

    var grupoDAO: GrupoDAO? = AppDatabase.getInstance(aplication)?.grupoDAO()

    fun obtenerGrupos(): LiveData<List<GrupoEntity>> = grupoDAO?.obtenerGrupos() ?: MutableLiveData()

    fun crearGrupo(grupoEntity: GrupoEntity) = grupoDAO?.let { InsertAsyncTask(
        it
    ).execute(grupoEntity) }

    private class InsertAsyncTask(private val contactDao: GrupoDAO) :
        AsyncTask<GrupoEntity, Void, Void>() {
        override fun doInBackground(vararg grupos: GrupoEntity?): Void? {
            for (grupo in grupos) {
                if (grupo != null) contactDao.crearGrupo(grupo)
            }
            return null
        }
    }

}