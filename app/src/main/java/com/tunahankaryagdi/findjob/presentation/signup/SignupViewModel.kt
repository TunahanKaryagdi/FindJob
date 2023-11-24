package com.tunahankaryagdi.findjob.presentation.signup

import androidx.lifecycle.ViewModel
import com.tunahankaryagdi.findjob.domain.use_case.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val createUserUseCase: CreateUserUseCase) : ViewModel() {

    private val _uiState : MutableStateFlow<SignupUiState> = MutableStateFlow(SignupUiState())
    val uiState = _uiState

}


data class SignupUiState(
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = ""
)