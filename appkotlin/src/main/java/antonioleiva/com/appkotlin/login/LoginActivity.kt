package antonioleiva.com.appkotlin.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import antonioleiva.com.appkotlin.R
import antonioleiva.com.appkotlin.databinding.ActivityLoginBinding
import antonioleiva.com.appkotlin.main.MainActivity

class LoginActivity : AppCompatActivity(), LoginView {

    private val presenter = LoginPresenter(this, LoginInteractor())
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // https://developer.android.com/topic/libraries/view-binding?hl=zh-cn#kotlin
        binding = ActivityLoginBinding.inflate((layoutInflater))
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener { validateCredentials() }
    }

    private fun validateCredentials() {
        presenter.validateCredentials(binding.username.text.toString(), binding.password.text.toString())
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progress.visibility = View.GONE
    }

    override fun setUsernameError() {
        binding.username.error = getString(R.string.username_error)
    }

    override fun setPasswordError() {
        binding.password.error = getString(R.string.password_error)
    }

    override fun navigateToHome() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}
