package pe.speira.antropometria.room.entities

import androidx.room.Embedded
import androidx.room.Relation

class PacienteControlEntity {

    @Embedded
    var pacienteEntity: PacienteEntity? = null

    @Relation(entity = ControlEntity::class, parentColumn = "dni", entityColumn = "pacienteDni")
    var controlList: List<ControlEntity> = ArrayList()

}