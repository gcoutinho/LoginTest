package com.testproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.testproject.data.model.LoginResult
import com.testproject.domain.LoginInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

sealed class LoginViewModelState {
    object ShowLoading: LoginViewModelState()
    object HideLoading: LoginViewModelState()
    object Success : LoginViewModelState()
    data class Error(val message: String) : LoginViewModelState()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    val interactor: LoginInteractor
): ViewModel() {

    val state = MutableLiveData<LoginViewModelState>()

    fun login(username: String, password: String) {

        state.value = LoginViewModelState.ShowLoading

        viewModelScope.launch {
            try {
                val result = interactor.login(username, password)

                state.value = LoginViewModelState.HideLoading
                when (result) {
                    is LoginResult.Success -> {
                        state.value = LoginViewModelState.Success
                    }

                    is LoginResult.Error -> {
                        state.value = LoginViewModelState.Error(result.message)
                    }

                    else -> {
                        state.value = LoginViewModelState.Error("Login failed")
                    }
                }
            } catch (e: Exception) {
                state.value = LoginViewModelState.HideLoading
                state.value = LoginViewModelState.Error(e.message ?: "An error occurred, please try again")
            }
        }
    }
}