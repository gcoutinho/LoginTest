package com.testproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.testproject.data.model.LoginResult
import com.testproject.domain.LoginInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

sealed class LoginViewModelState {
    object Loading: LoginViewModelState()
    object Success : LoginViewModelState()
    data class Error(val message: String) : LoginViewModelState()
}

@HiltViewModel
class LoginViewModel(
    val interactor: LoginInteractor
): ViewModel() {

    val state = MutableLiveData<LoginViewModelState>()

    fun login(username: String, password: String) {

        state.value = LoginViewModelState.Loading

        viewModelScope.launch {
            val result = interactor.login(username, password)

            if (result is LoginResult.Success) {
                state.value = LoginViewModelState.Success
            } else if( result is LoginResult.Error) {
                state.value = LoginViewModelState.Error(result.message)
            } else {
                state.value = LoginViewModelState.Error("Login failed")
            }
        }
    }
}