package pe.speira.antropometria.presentation.registrarGrupo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_registrar_grupo.*
import pe.speira.antropometria.R
import pe.speira.antropometria.room.entities.GrupoEntity
import pe.speira.antropometria.viewmodel.GrupoViewModel
import java.util.*

class RegistrarGrupoFragment : BottomSheetDialogFragment() {

    private lateinit var grupoViewModel: GrupoViewModel

    companion object {
        fun newInstance(): RegistrarGrupoFragment = RegistrarGrupoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setupViewModel()
        return inflater.inflate(R.layout.fragment_registrar_grupo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    private fun setupViewModel() {
        grupoViewModel = ViewModelProvider(this).get(GrupoViewModel::class.java)
    }

    private fun setupButtons() {
        btn_registrar_grupo.setOnClickListener {
            if (!edt_grupo.text?.trim().isNullOrEmpty()) {
                grupoViewModel.crearGrupo(
                    GrupoEntity(0, edt_grupo.text?.trim().toString(), Date())
                )
                dismiss()
            } else {
                Toast.makeText(context, "Ingrese un nombre válido", Toast.LENGTH_SHORT).show()
            }
        }
    }

}