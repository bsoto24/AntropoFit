package pe.speira.antropometria.presentation.detallePaciente

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_detalle_paciente.*
import pe.speira.antropometria.R
import pe.speira.antropometria.room.entities.PacienteEntity
import pe.speira.antropometria.viewmodel.ControlViewModel
import pe.speira.antropometria.presentation.registroControl.RegistroControlActivity
import pe.speira.antropometria.presentation.resultado.ResultadosActivity
import pe.speira.antropometria.utils.ApplicationUtils.Companion.obtenerEdad

class DetallePacienteActivity : AppCompatActivity() {

    private lateinit var adapter: DetallePacienteAdapter
    private var pacienteEntity: PacienteEntity? = null
    private lateinit var controlViewModel: ControlViewModel
    private lateinit var touchHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_paciente)
        setupViewModel()
        obtenerPaciente()
        setupToolbar("${pacienteEntity?.nombre} ${pacienteEntity?.apellidoPaterno}", true)
        setupPaciente(pacienteEntity)
        setupAdapter()
        setupSwipe()
        setupButtons()
        setupObserver()
    }

    private fun setupSwipe() {
        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    controlViewModel.eliminarControl(adapter.get(viewHolder.adapterPosition))
                }
            }
        touchHelper = ItemTouchHelper(itemTouchHelperCallback)
        touchHelper.attachToRecyclerView(rv_controles)

    }

    private fun setupViewModel() {
        controlViewModel = ViewModelProvider(this).get(ControlViewModel::class.java)
    }

    private fun obtenerPaciente() {
        pacienteEntity = intent.getParcelableExtra("paciente")

        pacienteEntity?.let {
            controlViewModel.obtenerPacienteControl(it.dni)
                ?.observe(this, Observer { pacienteControlEntity ->
                    Log.e("PACIENTE:", pacienteControlEntity.pacienteEntity.toString())
                    pacienteControlEntity.controlList.forEach { control ->
                        Log.e(
                            "CONTROL",
                            control.toString()
                        )
                    }
                })
        }

    }

    private fun setupObserver() {
        pacienteEntity?.let { paciente ->
            controlViewModel.obtenerControles(paciente.dni)
                .observe(this, Observer { controles ->
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
    }

    private fun setupToolbar(titulo: String?, upEnable: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = titulo
        supportActionBar?.setDisplayHomeAsUpEnabled(upEnable)
    }

    private fun setupPaciente(pacienteEntity: PacienteEntity?) {
        pacienteEntity?.let { paciente ->
            tv_edad.text = "${obtenerEdad(paciente.fechaNacimiento)} años"
            if (paciente.sexo == 0) tv_sexo.text = "Femenino" else tv_sexo.text = "Masculino"
        }
    }

    private fun setupAdapter() {
        adapter = DetallePacienteAdapter(ArrayList(), this) { control ->
            pacienteEntity?.let { paciente ->
                val intent = Intent(this, ResultadosActivity::class.java)
                intent.putExtra("control", control)
                intent.putExtra("paciente", paciente)
                startActivity(intent)
            }
        }
        rv_controles.layoutManager = LinearLayoutManager(this)
        rv_controles.adapter = adapter

    }

    private fun setupButtons() {
        fab_registro_control.setOnClickListener {
            pacienteEntity?.let { paciente ->
                val intent = Intent(this, RegistroControlActivity::class.java)
                intent.putExtra("paciente", paciente)
                startActivity(intent)
            }
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
