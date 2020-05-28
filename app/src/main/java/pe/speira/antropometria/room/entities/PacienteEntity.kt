package pe.speira.antropometria.room.entities

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(
    tableName = "paciente_table",
    foreignKeys = [
        ForeignKey(
            entity = GrupoEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("grupoId"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class PacienteEntity(

    @PrimaryKey(autoGenerate = false) var dni: String,
    @ColumnInfo(name = "grupoId") var grupoId: Int,
    @ColumnInfo(name = "nombre") var nombre: String,
    @ColumnInfo(name = "apellido_paterno") var apellidoPaterno: String,
    @ColumnInfo(name = "apellido_materno") var apellidoMaterno: String,
    @ColumnInfo(name = "fecha_nacimiento") var fechaNacimiento: Date,
    @ColumnInfo(name = "sexo") var sexo: Int

) : Parcelable {

    override fun toString(): String {
        return "PacienteEntity(dni='$dni', grupoId=$grupoId, nombre='$nombre', apellidoPaterno='$apellidoPaterno', apellidoMaterno='$apellidoMaterno', fechaNacimiento=${fechaNacimiento.toString()}, sexo=$sexo)"
    }

}