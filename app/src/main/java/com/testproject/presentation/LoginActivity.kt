package com.testproject.presentation

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.testproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: AppCompatActivity() {

    lateinit var usernameField: EditText
    lateinit var passwordField: EditText
    lateinit var loginButton: Button

    val viewModel: LoginViewModel by viewModels()

    lateinit var loadingDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        setupView()
        setupListeners()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.state.observe(this) { state -> when (state) {
                is LoginViewModelState.ShowLoading -> {
                    showLoading()
                }
                is LoginViewModelState.Success -> {
                    showSuccessMessage()
                }
                is LoginViewModelState.Error -> {
                    showError(state.message)
                }

                LoginViewModelState.HideLoading -> {
                    hideLoading()
                }
            }
        }
    }

    fun hideLoading() {
        if (::loadingDialog.isInitialized && loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, "Error: $message", Toast.LENGTH_SHORT).show()
    }

    private fun showSuccessMessage() {
        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        loadingDialog = AlertDialog.Builder(this)
            .setTitle("Loading")
            .setMessage("Please wait...")
            .setCancelable(false)
            .show()
    }

    private fun setupView() {
        usernameField = findViewById(R.id.login_username)
        passwordField = findViewById(R.id.login_password)
        loginButton = findViewById(R.id.login_button)
    }

    private fun setupListeners() {
        loginButton.setOnClickListener {
            val username = usernameField.text.toString()
            val password = passwordField.text.toString()

            viewModel.login(username, password)
        }
    }
}