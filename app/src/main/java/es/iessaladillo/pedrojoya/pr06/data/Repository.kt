package es.iessaladillo.pedrojoya.pr06.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.iessaladillo.pedrojoya.pr06.data.DataSource
import es.iessaladillo.pedrojoya.pr06.data.model.User

object Repository  : DataSource {
    private var users :MutableList<User> = mutableListOf()
    private val usersLiveData : MutableLiveData<List<User>> = MutableLiveData(users)

    override fun getAllUsersOrderedByName(): LiveData<List<User>> {
        return usersLiveData
    }

    override fun insertUser(user: User) {
        users.add(user)
        usersLiveData.value= ArrayList<User>(users)

    }

    override fun updateUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(user: User) {
        TODO("Not yet implemented")
    }
}