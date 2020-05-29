package pe.speira.antropometria.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deporte_table")
data class DeporteEntity(
    @PrimaryKey(autoGenerate = true) val deporteId: Int,
    val descripcion: String
)