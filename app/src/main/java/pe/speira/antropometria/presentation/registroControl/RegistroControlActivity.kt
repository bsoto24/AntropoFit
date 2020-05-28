package pe.speira.antropometria.presentation.registroControl

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_registro_control.*
import pe.speira.antropometria.R
import pe.speira.antropometria.room.entities.ControlEntity
import pe.speira.antropometria.room.entities.PacienteEntity
import pe.speira.antropometria.room.viewmodel.ControlViewModel
import pe.speira.antropometria.presentation.resultado.ResultadosActivity
import pe.speira.antropometria.room.entities.AntropometriaEntity
import java.util.*

class RegistroControlActivity : AppCompatActivity() {

    private var pacienteEntity: PacienteEntity? = null
    private lateinit var controlViewModel: ControlViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_control)
        setupViewModel()
        obtenerPaciente()
        setupButtons()
    }

    private fun setupViewModel() {
        controlViewModel = ViewModelProvider(this).get(ControlViewModel::class.java)
    }

    private fun obtenerPaciente() {
        pacienteEntity = intent.getParcelableExtra("paciente")
    }

    private fun setupButtons() {
        btn_registrar_control.setOnClickListener {
            if (
                edt_peso.text.toString().trim().isNotEmpty() and
                edt_talla.text.toString().trim().isNotEmpty() and
                edt_pSuprailiaco.text.toString().trim().isNotEmpty() and
                edt_pSubEscapital.text.toString().trim().isNotEmpty() and
                edt_pBicipital.text.toString().trim().isNotEmpty() and
                edt_pTricipital.text.toString().trim().isNotEmpty()
            ) {
                pacienteEntity?.let { paciente ->
                    val controlEntity = ControlEntity(
                        0,
                        pacienteDni = paciente.dni,
                        peso = edt_peso.text.toString().toDouble(),
                        talla = edt_talla.text.toString().toDouble(),
                        antropometria = AntropometriaEntity(
                            pSuprailiaco = edt_pSuprailiaco.text.toString().toDouble(),
                            pSubescapular = edt_pSubEscapital.text.toString().toDouble(),
                            pBicipital = edt_pBicipital.text.toString().toDouble(),
                            pTricipital = edt_pTricipital.text.toString().toDouble()
                        ),
                        fechaRegistro = Date()
                    )
                    controlViewModel.registrarControl(controlEntity)
                    val intent = Intent(this, ResultadosActivity::class.java)
                    intent.putExtra("control", controlEntity)
                    intent.putExtra("paciente", paciente)
                    startActivity(intent)
                    finish()
                }
            } else {
                Snackbar.make(container, "Todos los campos son obligatorios", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
        btn_cerrar.setOnClickListener {
            onBackPressed()
        }
    }
}
