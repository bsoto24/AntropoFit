package pe.speira.antropometria.room.entities

import androidx.room.Entity

@Entity(primaryKeys = ["grupoId", "etiquetaId"])
data class GrupoEtiquetaCrossRef(
    val grupoId: Int,
    val etiquetaId: Int
)