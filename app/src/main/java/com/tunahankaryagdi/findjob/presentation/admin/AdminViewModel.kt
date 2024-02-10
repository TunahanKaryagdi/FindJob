package com.tunahankaryagdi.findjob.presentation.admin

import com.tunahankaryagdi.findjob.domain.model.job.Job
import com.tunahankaryagdi.findjob.domain.model.user.UserDetail
import com.tunahankaryagdi.findjob.domain.use_case.job.DeleteJobByIdUseCase
import com.tunahankaryagdi.findjob.domain.use_case.user.DeleteUserByUserIdUseCase
import com.tunahankaryagdi.findjob.domain.use_case.job.GetJobUseCase
import com.tunahankaryagdi.findjob.domain.use_case.user.GetUsersUseCase
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AdminViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val deleteUserByUserIdUseCase: DeleteUserByUserIdUseCase,
    private val getJobUseCase: GetJobUseCase,
    private val deleteJobByIdUseCase: DeleteJobByIdUseCase

): BaseViewModel<AdminUiState,AdminEffect,AdminEvent>() {
    override fun setInitialState(): AdminUiState =  AdminUiState()

    override fun handleEvents(event: AdminEvent) {
    }
}


data class AdminUiState(
    val isLoading: Boolean = false,
    val users: List<UserDetail> = emptyList(),
    val jobs: List<Job> = emptyList()
) : State


sealed interface AdminEffect : Effect{

}

sealed interface AdminEvent: Event{

}
