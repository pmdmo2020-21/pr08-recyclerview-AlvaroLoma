package es.iessaladillo.pedrojoya.pr06.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.iessaladillo.pedrojoya.pr06.data.DataSource
import es.iessaladillo.pedrojoya.pr06.data.model.User

object Repository  : DataSource {
    private var users :MutableList<User> = mutableListOf()
    private val usersLiveData : MutableLiveData<List<User>> = MutableLiveData(users)
    private var id:Long = 0
    override fun getAllUsersOrderedByName(): LiveData<List<User>> {
        users.sortBy { user -> user.nombre }
        updateUsersLiveData()
        return  usersLiveData
    }

    override fun insertUser(user: User) {
        id++;
        user.id=id
        users.add(user)
        updateUsersLiveData()

    }

    override fun updateUser(user: User) {
        val position = users.indexOfFirst { it.id ==user.id}
        if(position>=0){
            users.set(position,user)
            updateUsersLiveData()
        }
    }

    private fun updateUsersLiveData() {
        usersLiveData.value= ArrayList<User>(users)
    }

    override fun deleteUser(user: User) {
        users.remove(user)
        updateUsersLiveData()
    }
}