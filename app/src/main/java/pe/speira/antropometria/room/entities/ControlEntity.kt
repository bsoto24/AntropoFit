package pe.speira.antropometria.room.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.util.*

@Parcelize
@Entity(tableName = "control_table")
data class ControlEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "control_id") var controlId: Int,
    @ColumnInfo(name = "paciente_dni") var pacienteDni: String,
    @ColumnInfo(name = "talla") var talla: Double,
    @ColumnInfo(name = "peso") var peso: Double,
    @Embedded var antropometria: AntropometriaEntity,
    @ColumnInfo(name = "fecha_registro") var fechaRegistro: Date
) : Parcelable {

    override fun toString(): String {
        return "ControlEntity(controlId=$controlId, pacienteDni='$pacienteDni', talla=$talla, peso=$peso, pTricipital=${antropometria.pTricipital}, pBicipital=${antropometria.pBicipital}, pSubescapular=${antropometria.pSubescapular}, pSuprailiaco=${antropometria.pSuprailiaco}, fechaRegistro='${fechaRegistro.toString()}')"
    }

}