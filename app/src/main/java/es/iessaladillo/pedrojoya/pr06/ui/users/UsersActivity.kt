package es.iessaladillo.pedrojoya.pr06.ui.users

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.*
import es.iessaladillo.pedrojoya.pr06.R
import es.iessaladillo.pedrojoya.pr06.data.Repository
import es.iessaladillo.pedrojoya.pr06.data.model.User
import es.iessaladillo.pedrojoya.pr06.databinding.UserActivityBinding
import es.iessaladillo.pedrojoya.pr06.databinding.UsersActivityBinding
import es.iessaladillo.pedrojoya.pr06.ui.add_user.AddUserActivity
import es.iessaladillo.pedrojoya.pr06.ui.edit_user.EditUserActivity
import kotlinx.android.synthetic.main.users_activity.*
import kotlinx.android.synthetic.main.users_activity.view.*
import kotlinx.android.synthetic.main.users_activity_item.view.*

class UsersActivity : AppCompatActivity(), UsersActivityAdapter.onItemClick{

    // TODO: Código de la actividad.
    //  Ten en cuenta que el recyclerview se muestra en forma de grid,
    //  donde el número de columnas está gestionado
    //  por R.integer.users_grid_columns
    //  ...
    private val viewModel: UserActivityViewModel by viewModels{
        UserActivityViewModelFactory(Repository)
    }
    val listAdapter:UsersActivityAdapter = UsersActivityAdapter().also {
        it.setOnClickListenner(this@UsersActivity)
    }
    private lateinit var binding: UsersActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UsersActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        observeUsers()
    }

    private fun observeUsers() {
        viewModel.users.observe(this){
            updateList(it)
        }
    }

    private fun updateList(list: List<User>) {

        listAdapter.submitList(list)
        binding.lblEmptyView.visibility = if (list.isEmpty()) View.VISIBLE else View.INVISIBLE

    }

    private fun setupView() {
        setupRecyclerView()
        binding.lblEmptyView.setOnClickListener(View.OnClickListener { onAddUser() })
        //binding.lstUsers.botonEditar.setOnClickListener(View.OnClickListener {  })

    }

    private fun setupRecyclerView() {
        binding.lstUsers.setHasFixedSize(true)
        binding.lstUsers.adapter =listAdapter
        binding.lstUsers.layoutManager = LinearLayoutManager(this)
        binding.lstUsers.addItemDecoration(DividerItemDecoration(this,RecyclerView.VERTICAL))
        binding.lstUsers.itemAnimator=DefaultItemAnimator()

    }
// NO TOCAR: Estos métodos gestionan el menú y su gestión

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.users, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuAdd) {
            onAddUser()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // FIN NO TOCAR

    fun onAddUser() {
        startActivity(AddUserActivity.newIntent(this))
    }

    override fun onClick(position: Int) {
        var id= listAdapter.getItemId(position)

        startActivity(EditUserActivity.newIntent(this, listAdapter.getItemById(id)!!))

    }


}