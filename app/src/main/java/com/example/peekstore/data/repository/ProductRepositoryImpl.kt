package com.example.peekstore.data.repository

import com.example.peekstore.data.dto.ProductDto
import com.example.peekstore.data.service.FirebaseModule
import com.example.peekstore.domain.repository.ProductRepository
import kotlinx.coroutines.tasks.await

class ProductRepositoryImpl: ProductRepository {

    private val firestore = FirebaseModule.firestore


    override suspend fun getProducts(): List<ProductDto> {
        return try {
            val snapshot = firestore.collection("products").get().await()
            snapshot.documents.mapNotNull { product ->
                product.toObject(ProductDto::class.java)
            }
        }catch (e:Exception){
            emptyList()
        }
    }
}