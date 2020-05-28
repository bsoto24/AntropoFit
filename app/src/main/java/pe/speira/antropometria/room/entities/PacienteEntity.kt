package pe.speira.antropometria.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "paciente_table")
data class PacienteEntity(

    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "dni") var dni: String = "",
    @ColumnInfo(name = "grupo_id") var grupoId: Int = -1,
    @ColumnInfo(name = "nombre") var nombre: String = "",
    @ColumnInfo(name = "apellido_paterno") var apellidoPaterno: String = "",
    @ColumnInfo(name = "apellido_materno") var apellidoMaterno: String = "",
    @ColumnInfo(name = "fecha_nacimiento") var fechaNacimiento: Date?,
    @ColumnInfo(name = "sexo") var sexo: Int = -1

) : Serializable {

    override fun toString(): String {
        return "PacienteEntity(dni='$dni', grupoId=$grupoId, nombre='$nombre', apellidoPaterno='$apellidoPaterno', apellidoMaterno='$apellidoMaterno', fechaNacimiento=${fechaNacimiento.toString()}, sexo=$sexo)"
    }

}