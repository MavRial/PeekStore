package com.example.peekstore.domain.repository

import com.example.peekstore.data.dto.ProductDto

interface ProductRepository {
    suspend fun getProducts(): List<ProductDto>
}