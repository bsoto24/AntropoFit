package pe.speira.antropometria.presentation.detallePaciente

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detalle_paciente.*
import pe.speira.antropometria.R
import pe.speira.antropometria.room.entities.PacienteEntity
import pe.speira.antropometria.room.viewmodel.ControlViewModel
import pe.speira.antropometria.presentation.registroControl.RegistroControlActivity
import pe.speira.antropometria.presentation.resultado.ResultadosActivity
import pe.speira.antropometria.utils.ApplicationUtils.Companion.obtenerEdad

class DetallePacienteActivity : AppCompatActivity() {

    lateinit var adapter: DetallePacienteAdapter
    lateinit var paciente: PacienteEntity
    lateinit var controlViewModel: ControlViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_paciente)
        setupViewModel()
        obtenerPaciente()
        setupToolbar("${paciente.nombre} ${paciente.apellidoPaterno}", true)
        setupPaciente(paciente)
        setupAdapter()
        setupButtons()
        setupObserver()
    }

    private fun setupViewModel() {
        controlViewModel = ViewModelProvider(this).get(ControlViewModel::class.java)
    }

    private fun obtenerPaciente() {
        paciente = intent?.extras?.get("paciente") as PacienteEntity
    }

    private fun setupObserver() {
        controlViewModel.obtenerControles(paciente.dni)
            .observe(this, Observer { controles ->
                controles.forEach { Log.e("CONTROL", it.toString()) }
                if (controles.isNotEmpty()) {
                    rv_controles.visibility = View.VISIBLE
                    img_empty.visibility = View.GONE
                } else {
                    rv_controles.visibility = View.GONE
                    img_empty.visibility = View.VISIBLE
                }
                adapter.update(controles)
            })
    }

    private fun setupToolbar(titulo: String, upEnable: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = titulo
        supportActionBar?.setDisplayHomeAsUpEnabled(upEnable)
    }

    private fun setupPaciente(paciente: PacienteEntity) {
        tv_edad.text = "${obtenerEdad(paciente.fechaNacimiento)} años"
        if (paciente.sexo == 0) tv_sexo.text = "Femenino" else tv_sexo.text = "Masculino"
    }

    private fun setupAdapter() {
        adapter = DetallePacienteAdapter(ArrayList(), this) { control ->
            val intent = Intent(this, ResultadosActivity::class.java)
            intent.putExtra("control", control)
            intent.putExtra("paciente", paciente)
            startActivity(intent)
        }
        rv_controles.layoutManager = LinearLayoutManager(this)
        rv_controles.adapter = adapter

    }

    private fun setupButtons() {
        fab_registro_control.setOnClickListener {
            val intent = Intent(this, RegistroControlActivity::class.java)
            intent.putExtra("paciente", paciente)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
