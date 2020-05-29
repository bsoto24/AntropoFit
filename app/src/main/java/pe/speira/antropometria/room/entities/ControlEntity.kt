package pe.speira.antropometria.room.entities

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(
    tableName = "control_table",
    foreignKeys = [
        ForeignKey(
            entity = PacienteEntity::class,
            parentColumns = arrayOf("dni"),
            childColumns = arrayOf("pacienteDni"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class ControlEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "pacienteDni", index = true) var pacienteDni: String,
    @ColumnInfo(name = "talla") var talla: Double,
    @ColumnInfo(name = "peso") var peso: Double,
    @ColumnInfo(name = "nota") var nota: String,
    @Embedded var antropometria: AntropometriaEntity,
    @ColumnInfo(name = "fecha_registro") var fechaRegistro: Date
) : Parcelable {

    override fun toString(): String {
        return "ControlEntity(controlId=$id, pacienteDni='$pacienteDni', talla=$talla, peso=$peso, pTricipital=${antropometria.pTricipital}, pBicipital=${antropometria.pBicipital}, pSubescapular=${antropometria.pSubescapular}, pSuprailiaco=${antropometria.pSuprailiaco}, fechaRegistro='${fechaRegistro.toString()}')"
    }

}