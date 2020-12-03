package es.iessaladillo.pedrojoya.pr06.ui.edit_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.pr06.data.DataSource

class EditUserActivityViewModelFactory(val repository: DataSource) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = EditUserViewModel(repository) as T

}