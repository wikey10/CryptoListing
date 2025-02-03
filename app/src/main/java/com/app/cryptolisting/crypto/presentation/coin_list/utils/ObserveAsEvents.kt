package com.app.cryptolisting.crypto.presentation.coin_list.utils

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.app.cryptolisting.crypto.presentation.coin_list.CoinListEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Composable
fun <T> ObserveAsEvents(
    events : Flow<T>,
    key1 : Any? =null,
    key2 : Any? =null,
    onEvent: (T)-> Unit
){
    val lifeCycleOwner =  androidx.lifecycle.compose.LocalLifecycleOwner.current
    LaunchedEffect(lifeCycleOwner.lifecycle,key1, key2) {
        lifeCycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate){
                events.collect (onEvent)
            }
        }
    }
}