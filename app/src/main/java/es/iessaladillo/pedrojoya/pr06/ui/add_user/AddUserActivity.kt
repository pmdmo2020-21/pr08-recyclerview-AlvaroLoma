package es.iessaladillo.pedrojoya.pr06.ui.add_user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr06.R
import es.iessaladillo.pedrojoya.pr06.data.Repository
import es.iessaladillo.pedrojoya.pr06.data.model.User
import es.iessaladillo.pedrojoya.pr06.databinding.*
import es.iessaladillo.pedrojoya.pr06.utils.loadUrl

class AddUserActivity : AppCompatActivity() {

    // TODO: Código de la actividad.
    //  ...

    companion object{
        fun newIntent(context: Context): Intent{
            return Intent(context, AddUserActivity::class.java)

        }
    }
    private lateinit var binding : AddUserActivityBinding
    private val viewModel: AddUserActivityViewModel by viewModels{
        AddUserActivityViewModelFactory(Repository)
    }
    private lateinit var imagenURL:String;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddUserActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        imagenURL=viewModel.getRandomImagen()
        binding.image.loadUrl(imagenURL)
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
    override fun onBackPressed() {
        onSave()
        finish()
        super.onBackPressed()
    }

    private fun onSave() {
        var user = User(1,binding.editNombre.text.toString(),binding.editEmail.text.toString(),binding.editTelefono.text.toString(),
                binding.editDirrecion.text.toString(),binding.editWeb.text.toString(),imagenURL)
        viewModel.addUser(user)
       finish()
        super.onBackPressed()
    }

}