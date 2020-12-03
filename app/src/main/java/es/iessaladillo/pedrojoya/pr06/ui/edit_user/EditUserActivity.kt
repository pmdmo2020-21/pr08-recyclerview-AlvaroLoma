package es.iessaladillo.pedrojoya.pr06.ui.edit_user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import es.iessaladillo.pedrojoya.pr06.R
import es.iessaladillo.pedrojoya.pr06.data.Repository
import es.iessaladillo.pedrojoya.pr06.data.model.User
import es.iessaladillo.pedrojoya.pr06.databinding.AddUserActivityBinding
import es.iessaladillo.pedrojoya.pr06.databinding.UsersActivityBinding
import es.iessaladillo.pedrojoya.pr06.ui.add_user.AddUserActivity
import es.iessaladillo.pedrojoya.pr06.ui.users.UserActivityViewModel
import es.iessaladillo.pedrojoya.pr06.ui.users.UserActivityViewModelFactory
import es.iessaladillo.pedrojoya.pr06.utils.loadUrl

class EditUserActivity : AppCompatActivity() {

    // TODO: Código de la actividad.
    //  Ten en cuenta que la actividad debe recibir a través del intent
    //  con el que es llamado el objeto User corresondiente
    //  ...
    companion object{
        fun newIntent(context: Context, user: User): Intent {
            return Intent(context, EditUserActivity::class.java)
                    .putExtra("USER",user)

        }
    }
    private lateinit var binding: AddUserActivityBinding
    private val viewModel: EditUserViewModel by viewModels{
        EditUserActivityViewModelFactory(Repository)
    }
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddUserActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        reciveData()
        setupView()
    }

    private fun reciveData() {
        user= intent.getParcelableExtra<User>("USER")!!


    }

    private fun setupView() {
        binding.image.setOnClickListener { binding.image.loadUrl(viewModel.getRandomImagen()) }
        binding.image.loadUrl(user.photoUrl)
        binding.editNombre.setText(user.nombre)
        binding.editTelefono.setText(user.phoneNumber)
        binding.editEmail.setText(user.email)
       // binding.editNombre.text=Editable.Factory.getInstance().newEditable("kzlxcblkjz")



    }

    // NO TOCAR: Estos métodos gestionan el menú y su gestión

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.user, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuSave) {
            onSave()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    // FIN NO TOCAR

    private fun onSave() {
       viewModel.editUser(user.copy( nombre = binding.editNombre.text.toString(),email = binding.editEmail.text.toString(),phoneNumber = binding.editTelefono.text.toString()))
       finish()
        super.onBackPressed()
    }

}