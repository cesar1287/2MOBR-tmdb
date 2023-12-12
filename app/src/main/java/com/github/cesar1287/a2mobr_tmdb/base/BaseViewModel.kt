package com.github.cesar1287.a2mobr_tmdb.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cesar1287.a2mobr_tmdb.utils.Command
import com.github.cesar1287.a2mobr_tmdb.utils.ResponseApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseViewModel : ViewModel() {

    lateinit var command: MutableLiveData<Command>

    fun <T> T.callApi(
        call: suspend ()-> ResponseApi,
        onSuccess: (Any?) -> Unit,
        onError: (() -> Unit?)? = null
    ) {
        command.postValue(Command.Loading(true))

        viewModelScope.launch(IO) {
            when(val response = call.invoke()) {
                is ResponseApi.Success -> {
                    command.postValue(Command.Loading(false))
                    onSuccess(response.data)
                }
                is ResponseApi.Error -> {
                    command.postValue(Command.Loading(false))
                    onError?.let {
                        withContext(Dispatchers.Main) { onError.invoke() }
                    } ?: run {
                        command.postValue(Command.Error(response.message))
                    }
                }
            }
        }
    }
}