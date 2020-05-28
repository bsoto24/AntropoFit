package pe.speira.antropometria.presentation.resultado

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_resultados.*
import pe.speira.antropometria.R
import pe.speira.antropometria.room.entities.ControlEntity
import pe.speira.antropometria.room.entities.PacienteEntity

class ResultadosActivity : AppCompatActivity() {

    private var controlEntity: ControlEntity? = null
    private var pacienteEntity: PacienteEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)
        obtenerControl()
        obtenerPaciente()
        setupControl(controlEntity)
        setupButtons()
    }

    private fun setupButtons() {
        btn_cerrar.setOnClickListener {
            finish()
        }
    }

    private fun obtenerControl() {
        controlEntity = intent.getParcelableExtra("control")
    }


    private fun obtenerPaciente() {
        pacienteEntity = intent.getParcelableExtra("paciente")
    }

    private fun setupControl(controlEntity: ControlEntity?) {
        controlEntity?.let { control ->
            tv_peso.text = "${String.format("%.2f", control.peso)} kg"
            tv_talla.text = "${String.format("%.2f", control.talla)} m"
            tv_pBicipital.text = String.format("%.2f", control.antropometria.pBicipital)
            tv_pSubEscapital.text = String.format("%.2f", control.antropometria.pSubescapular)
            tv_pSuprailiaco.text = String.format("%.2f", control.antropometria.pSuprailiaco)
            tv_pTricipital.text = String.format("%.2f", control.antropometria.pTricipital)
        }
    }

}
