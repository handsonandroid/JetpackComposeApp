package com.example.network// UserRepository.kt
// Repository to fetch users via ApiService
class UserRepository @Inject constructor(private val api: ApiService) {
    suspend fun getUsers(): List<User> = api.getUsers()
}
