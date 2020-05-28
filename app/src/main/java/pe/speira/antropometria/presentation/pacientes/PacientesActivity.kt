package pe.speira.antropometria.presentation.pacientes

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_pacientes.*
import pe.speira.antropometria.R
import pe.speira.antropometria.room.entities.GrupoEntity
import pe.speira.antropometria.room.entities.PacienteEntity
import pe.speira.antropometria.room.viewmodel.PacienteViewModel
import pe.speira.antropometria.presentation.detallePaciente.DetallePacienteActivity
import pe.speira.antropometria.presentation.registroPaciente.RegistroPacienteActivity

class PacientesActivity : AppCompatActivity() {

    lateinit var adapter: PacientesAdapter
    lateinit var grupo: GrupoEntity
    private lateinit var pacienteViewModel: PacienteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pacientes)
        setupViewModel()
        obtenerGrupo()
        setupToolbar(grupo.grupoNombre, true)
        setupAdapter()
        setupButtons()
        setupObserver()
    }

    private fun setupViewModel() {
        pacienteViewModel = ViewModelProvider(this).get(PacienteViewModel::class.java)
    }

    private fun setupObserver() {
        pacienteViewModel.obtenerPacientes(grupo.grupoId)
            .observe(this, Observer<List<PacienteEntity>> { pacientes ->
                if (pacientes.isNotEmpty()) {
                    rv_pacientes.visibility = View.VISIBLE
                    img_empty.visibility = View.GONE
                } else {
                    rv_pacientes.visibility = View.GONE
                    img_empty.visibility = View.VISIBLE
                }
                adapter.update(pacientes)
            })
    }

    private fun obtenerGrupo() {
        grupo = intent?.extras?.get("grupo") as GrupoEntity
    }

    private fun setupToolbar(titulo: String, upEnable: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = titulo
        supportActionBar?.setDisplayHomeAsUpEnabled(upEnable)
    }

    private fun setupAdapter() {
        adapter = PacientesAdapter(ArrayList(), this) { paciente ->
            val intent = Intent(this, DetallePacienteActivity::class.java)
            intent.putExtra("paciente", paciente)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
        rv_pacientes.layoutManager = LinearLayoutManager(this)
        rv_pacientes.adapter = adapter

    }

    private fun setupButtons() {
        fab_registro_paciente.setOnClickListener {
            val intent = Intent(this, RegistroPacienteActivity::class.java)
            intent.putExtra("grupo", grupo)
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