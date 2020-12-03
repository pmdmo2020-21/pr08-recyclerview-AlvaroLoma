package es.iessaladillo.pedrojoya.pr06.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr06.data.model.User
import es.iessaladillo.pedrojoya.pr06.databinding.UsersActivityItemBinding
import es.iessaladillo.pedrojoya.pr06.utils.loadUrl

class UsersActivityAdapter() : ListAdapter<User,UsersActivityAdapter.ViewHolder>(Userdiff ) {

    var onClickEdit:((Int)-> Unit)? = null
    var onClickDelete:((Int)-> Unit)? = null


    fun OnClickListenner(listener: ((Int)-> Unit)?){
        onClickEdit=listener
    }



   inner class ViewHolder(private val binding: UsersActivityItemBinding) :RecyclerView.ViewHolder(binding.root) {
            init {
                binding.botonEditar.setOnClickListener{ onClickEdit?.invoke(bindingAdapterPosition) }
                binding.botonBorrar.setOnClickListener{ onClickDelete?.invoke(bindingAdapterPosition) }

            }
        fun bind(user: User){
            binding.lblNombre.text=user.nombre
            binding.lblEmail.text=user.email
            binding.lblNumero.text=user.phoneNumber
            binding.imagenUser.loadUrl(user.photoUrl)


        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layoutInflater= LayoutInflater.from(parent.context)
       val binding=  UsersActivityItemBinding.inflate(layoutInflater, parent,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }



    object Userdiff : DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id==newItem.id
        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem.nombre==newItem.nombre && oldItem.email==newItem.email && oldItem.phoneNumber==newItem.phoneNumber
    }

}