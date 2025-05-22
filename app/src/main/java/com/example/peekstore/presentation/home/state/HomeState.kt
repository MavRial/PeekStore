package com.example.peekstore.presentation.home.state

import com.example.peekstore.data.dto.ProductDto

sealed class HomeState {
    object Loading : HomeState()
    data class Success(val products: List<ProductDto>) : HomeState()
    data class Error(val message: String): HomeState()
}