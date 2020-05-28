package pe.speira.antropometria.presentation.resultado

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_resultados.*
import pe.speira.antropometria.R
import pe.speira.antropometria.room.entities.ControlEntity
import pe.speira.antropometria.room.entities.PacienteEntity

class ResultadosActivity : AppCompatActivity() {

    lateinit var controlEntity: ControlEntity
    lateinit var pacienteEntity: PacienteEntity

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
        controlEntity = intent?.extras?.get("control") as ControlEntity
    }


    private fun obtenerPaciente() {
        pacienteEntity = intent?.extras?.get("paciente") as PacienteEntity
    }

    private fun setupControl(controlEntity: ControlEntity) {
        tv_peso.text = "${String.format("%.2f", controlEntity.peso)} kg"
        tv_talla.text = "${String.format("%.2f", controlEntity.talla)} m"
        tv_pBicipital.text = String.format("%.2f", controlEntity.pBicipital)
        tv_pSubEscapital.text = String.format("%.2f", controlEntity.pSubescapular)
        tv_pSuprailiaco.text = String.format("%.2f", controlEntity.pSuprailiaco)
        tv_pTricipital.text = String.format("%.2f", controlEntity.pTricipital)
    }

}
