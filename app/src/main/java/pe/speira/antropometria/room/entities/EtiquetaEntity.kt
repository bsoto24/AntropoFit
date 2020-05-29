package pe.speira.antropometria.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "etiqueta_table")
data class EtiquetaEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "nombre") var nombre: String
)