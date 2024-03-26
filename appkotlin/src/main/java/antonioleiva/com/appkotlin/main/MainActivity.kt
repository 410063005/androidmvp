package antonioleiva.com.appkotlin.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import antonioleiva.com.appkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private val presenter = MainPresenter(this, FindItemsInteractor())
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate((layoutInflater))
        val view = binding.root
        setContentView(view)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showProgress() {
        binding.progress.visibility = View.VISIBLE
        binding.list.visibility = View.GONE
    }

    override fun hideProgress() {
        binding.progress.visibility = View.GONE
        binding.list.visibility = View.VISIBLE
    }

    override fun setItems(items: List<String>) {
        binding.list.adapter = MainAdapter(items, presenter::onItemClicked)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
