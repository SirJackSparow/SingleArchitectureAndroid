package com.example.singlearchitecture.utilities

sealed class Resource<out D>(val data: D?, val message: String?) {
    class Success<D>(data: D?) : Resource<D>(data = data, message = null)
    class Error<D>(message: String?) : Resource<D>(data = null, message = message)
}
