package com.example.analytics

import android.os.Bundle

// AnalyticsService.kt
// Log Firebase analytics events
object AnalyticsService {
    private val analytics = Firebase.analytics

    fun logEvent(event: String, params: Map<String, String> = emptyMap()) {
        val bundle = Bundle().apply {
            params.forEach { (k, v) -> putString(k, v) }
        }
        analytics.logEvent(event, bundle)
    }
}
