package com.example.moviles1
import android.view.LayoutInflater
import android.view.TextureView
import android.view.ViewGroup
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class RecyclerAdaptador(
    private val listaUsuarios: List<UsuarioHttp>,
    private val contexto: RecycleActivity,
    private val recyclerView: androidx.recyclerview.widget.RecyclerView
) : androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerAdaptador.MyViewHolder>() {
    inner class MyViewHolder(view: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        var nombreTextView: TextView
        var cedulaTextView: TextView
        var accionButton: Button
        var likesTextView: TextView
        var numeroLikes = 0
        init {
            nombreTextView = view.findViewById(R.id.tv_nombre)
            cedulaTextView = view.findViewById(R.id.tv_cedula)
            accionButton = view.findViewById(R.id.btn_action)
            likesTextView = view.findViewById(R.id.tv_likes)
            accionButton.setOnClickListener {
                this.anadirLikes()
            }
        }
        fun anadirLikes(){
            this.numeroLikes = this.numeroLikes + 1
            likesTextView.text = this.numeroLikes.toString()
            contexto.anadirLikesEnActividad(1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdaptador.MyViewHolder {
        //Definimos la interfaz que vamos a usar
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.adapatador_persona,
                parent,
                false
            )
        return  MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val usuario = listaUsuarios[position]
        holder.nombreTextView.text = usuario.nombre
        holder.cedulaTextView.text = usuario.cadula
        holder.accionButton.text = "Like ${usuario.nombre}"
        holder.likesTextView.text = "0"
    }

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }
}