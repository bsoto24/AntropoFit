package pe.speira.antropometria.presentation.grupos

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_grupos.*
import pe.speira.antropometria.R
import pe.speira.antropometria.presentation.pacientes.PacientesActivity
import pe.speira.antropometria.presentation.registrarGrupo.RegistrarGrupoFragment
import pe.speira.antropometria.viewmodel.GrupoViewModel

class GruposActivity : AppCompatActivity() {

    private lateinit var grupoViewModel: GrupoViewModel
    lateinit var adapter: GruposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grupos)

        setupViewModel()
        setupAdapter()
        setupButtons()
        setupObserver()

    }

    private fun setupViewModel() {
        grupoViewModel = ViewModelProvider(this).get(GrupoViewModel::class.java)
    }

    private fun setupObserver() {
        grupoViewModel.obtenerGrupos().observe(this, Observer { grupos ->
            if (grupos.isNotEmpty()) {
                rv_grupos.visibility = View.VISIBLE
                img_empty.visibility = View.GONE
            } else {
                rv_grupos.visibility = View.GONE
                img_empty.visibility = View.VISIBLE
            }
            adapter.update(grupos)
        })
    }

    private fun setupButtons() {
        fab_registro_grupo.setOnClickListener {
            val fragment = RegistrarGrupoFragment.newInstance()
            fragment.show(supportFragmentManager, "registrar_grupo")
        }
    }

    private fun setupAdapter() {
        adapter = GruposAdapter(ArrayList()) { grupo ->
            val intent = Intent(this, PacientesActivity::class.java)
            intent.putExtra("grupo", grupo)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
        rv_grupos.layoutManager = LinearLayoutManager(this)
        rv_grupos.adapter = adapter
    }

}

