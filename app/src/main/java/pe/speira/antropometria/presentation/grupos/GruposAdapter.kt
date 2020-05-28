package pe.speira.antropometria.presentation.grupos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_grupo.view.*
import pe.speira.antropometria.R
import pe.speira.antropometria.room.entities.GrupoEntity

class GruposAdapter(
    private var items: List<GrupoEntity>,
    private var clickListener: (GrupoEntity) -> Unit
) : RecyclerView.Adapter<GruposAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_grupo, parent, false
        )
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    fun update(items: List<GrupoEntity>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(grupoEntity: GrupoEntity) {
            itemView.tv_grupo.text = grupoEntity.grupoNombre
            itemView.setOnClickListener { clickListener(grupoEntity) }
        }
    }
}