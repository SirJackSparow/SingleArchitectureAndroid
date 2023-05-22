package com.example.singlearchitecture.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.singlearchitecture.data.networks.model.UsersRandomModelItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel : ViewModel() {

    private var page = 1
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    private var query = "super"
    private var sort = ""
    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _uiState.value = HomeUiState.Error("")
    }

    val uiState: StateFlow<HomeUiState> get() = _uiState

    init {

    }

    fun nextPage() {
        viewModelScope.launch(exceptionHandler) {
            if (_uiState.value is HomeUiState.Success && (_uiState.value as HomeUiState.Success).loadNextPage.not()) {
                page++
                //_uiState.value = HomeUiState.Success(data = "")
                delay(1_000)
                loadList()
            }
        }
    }

    private fun loadList(
        q: String = query.ifEmpty { "super" },
        sort: String = this.sort
    ) {

    }
}

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Error(val message: String) : HomeUiState()
    data class Success(val data: List<UsersRandomModelItem>, val loadNextPage: Boolean) : HomeUiState()
}
