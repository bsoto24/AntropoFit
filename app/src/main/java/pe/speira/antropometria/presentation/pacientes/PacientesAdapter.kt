package pe.speira.antropometria.presentation.pacientes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_paciente.view.*
import pe.speira.antropometria.R
import pe.speira.antropometria.room.entities.PacienteEntity
import pe.speira.antropometria.utils.ApplicationUtils.Companion.obtenerEdad

class PacientesAdapter(
    var items: List<PacienteEntity>,
    val context: Context,
    var clickListener: (PacienteEntity) -> Unit
) : RecyclerView.Adapter<PacientesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_paciente, parent, false)
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    fun update(items: List<PacienteEntity>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun get(index: Int): PacienteEntity = items[index]

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(pacienteEntity: PacienteEntity) {
            itemView.tv_nombre.text =
                "${pacienteEntity.nombre} ${pacienteEntity.apellidoPaterno}"
            itemView.tv_edad.text = "${obtenerEdad(pacienteEntity.fechaNacimiento)} años"
            itemView.setOnClickListener { clickListener(pacienteEntity) }
        }

    }

}