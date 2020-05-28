package pe.speira.antropometria.presentation.detallePaciente

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_control.view.*
import pe.speira.antropometria.R
import pe.speira.antropometria.room.entities.ControlEntity
import java.text.SimpleDateFormat
import java.util.*

class DetallePacienteAdapter(
    private var items: List<ControlEntity>,
    val context: Context,
    private var clickListener: (ControlEntity) -> Unit
) : RecyclerView.Adapter<DetallePacienteAdapter.ViewHolder>() {

    private val formatter = SimpleDateFormat("d MMM yyyy", Locale.US)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_control, parent, false
        )
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    fun update(items: List<ControlEntity>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(controlEntity: ControlEntity) {

            itemView.tv_fecha.text = formatter.format(controlEntity.fechaRegistro)
            itemView.setOnClickListener { clickListener(controlEntity) }

        }

    }
}