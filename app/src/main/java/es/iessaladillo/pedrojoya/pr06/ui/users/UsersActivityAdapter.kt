package es.iessaladillo.pedrojoya.pr06.ui.users

import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr06.data.model.User
import es.iessaladillo.pedrojoya.pr06.databinding.UsersActivityItemBinding
import es.iessaladillo.pedrojoya.pr06.utils.loadUrl

class UsersActivityAdapter() : RecyclerView.Adapter<UsersActivityAdapter.ViewHolder>() {
    private var data : List<User> = emptyList()
    private var onClick:UsersActivityAdapter.onItemClick? =null
    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return data[position].id
    }
     fun getItemById(id: Long): User? {
         var usuarioD:User?=null
        for (usuarios in data){
            if(usuarios.id==id){
                usuarioD=usuarios
            }
        }
         return usuarioD
    }
    fun setOnClickListenner(listener: UsersActivityAdapter.onItemClick){
        onClick=listener
    }

   inner class ViewHolder(private val binding: UsersActivityItemBinding) :RecyclerView.ViewHolder(binding.root) {
            init {
                binding.botonEditar.setOnClickListener{ onClick?.onClick(bindingAdapterPosition) }
            }
        fun bind(user: User){
            binding.lblNombre.text=user.nombre
            binding.lblEmail.text=user.email
            binding.lblNumero.text=user.phoneNumber
            binding.imagenUser.loadUrl(user.photoUrl)


        }

    }
    interface onItemClick{
        fun onClick(position: Int)
    }

    fun submitList(lista: List<User>){
        data=lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layoutInflater= LayoutInflater.from(parent.context)
       val binding=  UsersActivityItemBinding.inflate(layoutInflater, parent,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}