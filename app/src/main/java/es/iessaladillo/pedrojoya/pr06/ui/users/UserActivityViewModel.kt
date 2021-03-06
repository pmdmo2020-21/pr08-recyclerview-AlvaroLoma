package es.iessaladillo.pedrojoya.pr06.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr06.data.DataSource
import es.iessaladillo.pedrojoya.pr06.data.model.User

class UserActivityViewModel (val repository: DataSource) : ViewModel() {





    val users: LiveData<List<User>> = repository.getAllUsersOrderedByName()

    fun deleteByPosition(user: User) {
        repository.deleteUser(user)
    }

}