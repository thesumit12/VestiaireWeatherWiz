package com.slakra.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    protected val _state = MutableLiveData<ProgressState>()
    val state: LiveData<ProgressState>
        get() = _state
    protected val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String>
    get() = _errorMsg

    /**
     * Function to parse error
     * @param result [ResultState]
     * @author sumitlakra
     * @date 06/06/2021
     */
    fun <T>parseError(result: ResultState<T>) {
        when(result) {
            is ResultState.Error -> {
                _errorMsg.value = result.errorMsg
                _state.value = ProgressState.ERROR
            }
            is ResultState.HttpErrors.BadGateWay -> {
                _errorMsg.value = result.exceptionMsg
                _state.value = ProgressState.ERROR
            }
            is ResultState.HttpErrors.InternalServerError -> {
                _errorMsg.value = result.exceptionMsg
                _state.value = ProgressState.ERROR
            }
            is ResultState.HttpErrors.RemovedResourceFound -> {
                _errorMsg.value = result.exceptionMsg
                _state.value = ProgressState.ERROR
            }
            is ResultState.HttpErrors.ResourceForbidden -> {
                _errorMsg.value = result.exceptionMsg
                _state.value = ProgressState.ERROR
            }
            is ResultState.HttpErrors.ResourceNotFound -> {
                _errorMsg.value = result.exceptionMsg
                _state.value = ProgressState.ERROR
            }
            is ResultState.HttpErrors.ResourceRemoved -> {
                _errorMsg.value = result.exceptionMsg
                _state.value = ProgressState.ERROR
            }
            is ResultState.InvalidData -> {
                _state.value = ProgressState.ERROR
            }
            is ResultState.NetworkException -> {
                _state.value = ProgressState.ERROR
            }
            else -> {
                _state.value = ProgressState.SUCCESS
            }
        }
    }
}