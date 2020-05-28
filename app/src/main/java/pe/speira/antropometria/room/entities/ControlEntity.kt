package pe.speira.antropometria.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "control_table")
data class ControlEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "control_id") var controlId: Int,
    @ColumnInfo(name = "paciente_dni") var pacienteDni: String,
    @ColumnInfo(name = "talla") var talla: Double,
    @ColumnInfo(name = "peso") var peso: Double,
    @ColumnInfo(name = "p_tricipital") var pTricipital: Double,
    @ColumnInfo(name = "p_bicipital") var pBicipital: Double,
    @ColumnInfo(name = "p_subescapular") var pSubescapular: Double,
    @ColumnInfo(name = "p_suprailiaco") var pSuprailiaco: Double,
    @ColumnInfo(name = "fecha_registro") var fechaRegistro: Date?
) : Serializable {

    override fun toString(): String {
        return "ControlEntity(controlId=$controlId, pacienteDni='$pacienteDni', talla=$talla, peso=$peso, pTricipital=$pTricipital, pBicipital=$pBicipital, pSubescapular=$pSubescapular, pSuprailiaco=$pSuprailiaco, fechaRegistro='${fechaRegistro.toString()}')"
    }

}