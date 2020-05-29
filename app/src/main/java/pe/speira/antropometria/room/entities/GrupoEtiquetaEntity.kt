package pe.speira.antropometria.room.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(tableName = "etiqueta_table")
data class GrupoEtiquetaEntity(
    @Embedded val grupo: GrupoEntity,
    @Relation(
        parentColumn = "grupoId",
        entityColumn = "etiquetaId",
        associateBy = Junction(GrupoEtiquetaCrossRef::class)
    ) val etiquetas: List<EtiquetaEntity>
)