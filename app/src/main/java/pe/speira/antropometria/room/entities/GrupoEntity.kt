package pe.speira.antropometria.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "grupo_table")
data class GrupoEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "grupo_id") var grupoId: Int,
    @ColumnInfo(name = "grupo_nombre") var grupoNombre: String,
    @ColumnInfo(name = "fecha_creacion") var fechaCreacion: Date?
) : Serializable {

    override fun toString(): String {
        return "GrupoEntity(grupoId=$grupoId, grupoNombre='$grupoNombre', fechaCreacion=${fechaCreacion.toString()})"
    }
}