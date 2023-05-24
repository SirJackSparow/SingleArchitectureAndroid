package com.example.singlearchitecture.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.singlearchitecture.data.networks.model.UsersRandomModelItem
import com.example.singlearchitecture.domain.GetUsersDataUseCase
import com.example.singlearchitecture.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCaseUser: GetUsersDataUseCase) :
    ViewModel() {

    private var page = 1
    private var query = "super"
    private var sort = ""
    private var listUsersData = mutableListOf<UsersRandomModelItem>()
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _uiState.value = HomeUiState.Error("")
    }
    val uiState: StateFlow<HomeUiState> get() = _uiState

    init {
        loadList()
    }

    fun nextPage() {
        viewModelScope.launch(exceptionHandler) {
            if (_uiState.value is HomeUiState.Success && (_uiState.value as HomeUiState.Success).loadNextPage.not()) {
                page++
                _uiState.value = HomeUiState.Success(data = listUsersData, loadNextPage = true)
                delay(1_000)
                loadList()
            }
        }
    }

    private fun loadList(
        q: String = query.ifEmpty { "super" },
        sort: String = this.sort
    ) {
        viewModelScope.launch(exceptionHandler) {
            useCaseUser(q = q, page = page, sort = sort).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        listUsersData.addAll(result.data ?: emptyList())
                        _uiState.value = HomeUiState.Success(
                            data = listUsersData,
                            loadNextPage = false
                        )
                    }

                    is Resource.Error -> _uiState.value = HomeUiState.Error(result.message ?: "")
                }
            }
        }
    }
}

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Error(val message: String) : HomeUiState()
    data class Success(val data: List<UsersRandomModelItem>, val loadNextPage: Boolean) :
        HomeUiState()
}
