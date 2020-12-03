package es.iessaladillo.pedrojoya.pr06.ui.add_user

import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import es.iessaladillo.pedrojoya.pr06.data.DataSource
import es.iessaladillo.pedrojoya.pr06.data.model.User
import kotlin.random.Random

// TODO:
//  Crear la clase EditUserViewModel. Ten en cuenta que la url de la photo
//  deberá ser preservada por si la actividad es destruida por falta de recursos.

class AddUserActivityViewModel(val repository: DataSource) : ViewModel()  {

    private  var random= Random



    // Para obtener un URL de foto de forma aleatoria (tendrás que definir
    // e inicializar el random a nivel de clase.
    private fun getRandomPhotoUrl(): String =
            "https://picsum.photos/id/${random.nextInt(100)}/400/300"

    fun addUser(user: User): Boolean {

        if(!user.nombre.isEmpty() && !user.nombre.isBlank()){
            if(!user.email.isEmpty() && !user.email.isBlank() ){
                if(!user.phoneNumber.isEmpty() && !user.phoneNumber.isBlank()){
                    repository.insertUser(user)
                    return true;
                }
            }
        }
            return false;
    }

    fun getRandomImagen(): String {
        return getRandomPhotoUrl()
    }

}
