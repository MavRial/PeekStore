package com.example.peekstore.presentation.home.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.peekstore.data.dto.ProductDto
import com.example.peekstore.data.repository.ProductRepositoryImpl
import com.example.peekstore.domain.repository.ProductRepository
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.peekstore.data.service.FirebaseModule
import com.example.peekstore.data.service.TokenManager
import com.example.peekstore.presentation.home.state.HomeState
import kotlinx.coroutines.launch


class HomeViewModel(
    private val productRepository: ProductRepository = ProductRepositoryImpl()
): ViewModel() {

    private val _state = mutableStateOf<HomeState>(HomeState.Loading)
    val state: State<HomeState> get() = _state

    private val _searchQuery = mutableStateOf("")
    val searchQuery : State<String> get() = _searchQuery


    private var allProducts: List<ProductDto> = emptyList()

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            _state.value = HomeState.Loading

            try {
                val products = productRepository.getProducts()
                allProducts = products
                _state.value = HomeState.Success(products)
            }catch (e:Exception){
                _state.value = HomeState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
        val filtered = allProducts.filter { it.name.contains(newQuery, ignoreCase = true) }
        _state.value = HomeState.Success(filtered)
    }

    fun logout(
        uid: String,
        context : Context,
        onLoggedOut: ()-> Unit
    ){
        viewModelScope.launch {
            Log.d("Logout", "User $uid logged out")
            TokenManager.clearUserUid(context)
            onLoggedOut()
        }
    }
}