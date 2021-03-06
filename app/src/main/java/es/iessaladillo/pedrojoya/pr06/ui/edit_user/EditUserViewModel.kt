package es.iessaladillo.pedrojoya.pr06.ui.edit_user

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr06.data.DataSource
import es.iessaladillo.pedrojoya.pr06.data.model.User
import kotlin.random.Random

// TODO:
//  Crear la clase EditUserViewModel. Ten en cuenta que la url de la photo
//  deberá ser preservada por si la actividad es destruida por falta de recursos.

class EditUserViewModel( val repository: DataSource): ViewModel() {
    private  var random= Random
    // Para obtener un URL de foto de forma aleatoria (tendrás que definir
    // e inicializar el random a nivel de clase.
    private fun getRandomPhotoUrl(): String = "https://picsum.photos/id/${random.nextInt(100)}/400/300"

    fun editUser(user: User){
        repository.updateUser(user)

    }

    fun getRandomImagen(): String = getRandomPhotoUrl()
}
