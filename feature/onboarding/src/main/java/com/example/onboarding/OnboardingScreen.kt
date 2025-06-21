package com.example.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.common.AppPreferences
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(onDone: () -> Unit) {
    val context = LocalContext.current
    //val pagerState = rememberPagerState()
    val pagerState = rememberPagerState { 10 }
    val scope = rememberCoroutineScope()

    fun completeOnboarding() {
        AppPreferences.setFirstLaunch(context, false)
        onDone()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //HorizontalPager(count = 3, state = pagerState, modifier = Modifier.weight(1f)) { page ->
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
            Column(
                modifier = Modifier.fillMaxSize().padding(32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Welcome Page ${page + 1}", style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(12.dp))
                Text("Brief intro about your app here.")
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            if (pagerState.currentPage < 2) {
                TextButton(onClick = { completeOnboarding() }) {
                    Text("Skip")
                }
            }

            Button(onClick = {
                if (pagerState.currentPage == 2) completeOnboarding()
                else scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
            }) {
                Text(if (pagerState.currentPage == 2) "Done" else "Next")
            }
        }
    }
}
