package com.example.auth// AuthService.kt
// Firebase login/logout helpers
object AuthService {
    private val auth = FirebaseAuth.getInstance()

    fun isUserLoggedIn(): Boolean = auth.currentUser != null

    suspend fun login(email: String, password: String): Result<Unit> = try {
        auth.signInWithEmailAndPassword(email, password).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    fun logout() = auth.signOut()
}
