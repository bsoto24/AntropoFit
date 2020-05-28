package pe.speira.antropometria.presentation.registroPaciente

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_registro_paciente.*
import kotlinx.android.synthetic.main.activity_registro_paciente.btn_cerrar
import kotlinx.android.synthetic.main.activity_registro_paciente.container
import pe.speira.antropometria.R
import pe.speira.antropometria.room.entities.GrupoEntity
import pe.speira.antropometria.room.entities.PacienteEntity
import pe.speira.antropometria.viewmodel.PacienteViewModel
import pe.speira.antropometria.utils.DatePickerFragment
import java.text.SimpleDateFormat
import java.util.*

class RegistroPacienteActivity : AppCompatActivity() {

    private var grupoEntity: GrupoEntity? = null
    private lateinit var pacienteViewModel: PacienteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_paciente)
        setupViewModel()
        obtenerGrupo()
        setupButtons()
    }

    private fun setupViewModel() {
        pacienteViewModel = ViewModelProvider(this).get(PacienteViewModel::class.java)
    }

    private fun obtenerGrupo() {
        grupoEntity = intent.getParcelableExtra("grupo")
    }

    private fun setupButtons() {

        edt_fecha_nacimiento.setOnClickListener {
            showDatePickerDialog()
        }

        btn_registrar_paciente.setOnClickListener {
            if (
                edt_dni.text.toString().trim().isNotEmpty() and
                edt_nombres.text.toString().trim().isNotEmpty() and
                edt_apellido_paterno.text.toString().trim().isNotEmpty() and
                edt_apellido_materno.text.toString().trim().isNotEmpty() and
                edt_fecha_nacimiento.text.toString().trim().isNotEmpty()
            ) {
                grupoEntity?.let { grupo ->
                    pacienteViewModel.registrarPaciente(
                        PacienteEntity(
                            grupoId = grupo.id,
                            dni = edt_dni.text.toString(),
                            nombre = edt_nombres.text.toString(),
                            apellidoPaterno = edt_apellido_paterno.text.toString(),
                            apellidoMaterno = edt_apellido_materno.text.toString(),
                            fechaNacimiento = SimpleDateFormat("yyyy/M/d", Locale.ENGLISH).parse(edt_fecha_nacimiento.text.toString().trim()),
                            sexo = spn_sexo.selectedItemPosition
                        )
                    )
                    finish()
                }
            } else {
                Snackbar.make(container, "Todos los campos son obligatorios", Snackbar.LENGTH_SHORT).show()
            }
        }

        btn_cerrar.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showDatePickerDialog() {

        val fragment =
            DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
                edt_fecha_nacimiento.setText("$year/${month + 1}/$day")
            })

        fragment.show(supportFragmentManager, "datePicker")
    }

}
