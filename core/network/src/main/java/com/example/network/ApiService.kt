package com.example.network// ApiService.kt
// Retrofit interface with API endpoints
interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}
