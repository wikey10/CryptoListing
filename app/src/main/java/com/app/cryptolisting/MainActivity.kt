package com.app.cryptolisting

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.cryptolisting.core.domain_util.navigation.AdaptiveCoinListDetailPane
import com.app.cryptolisting.crypto.presentation.coin_detail.CoinDetailScreen
import com.app.cryptolisting.crypto.presentation.coin_list.CoinListEvent
import com.app.cryptolisting.crypto.presentation.coin_list.CoinListScreen
import com.app.cryptolisting.crypto.presentation.coin_list.CoinListViewModel
import com.app.cryptolisting.crypto.presentation.coin_list.utils.ObserveAsEvents
import com.app.cryptolisting.crypto.presentation.coin_list.utils.toString
import com.app.cryptolisting.ui.theme.CryptoListingTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoListingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AdaptiveCoinListDetailPane(
                        modifier = Modifier.padding(innerPadding)
                    )

//                    val viewModel = koinViewModel<CoinListViewModel>()
//                    val state by viewModel.state.collectAsStateWithLifecycle()
//
//                    when {
//                        state.selectedCoin !=null -> {
//                            CoinDetailScreen(
//                                state = state,
//                                modifier = Modifier.padding(innerPadding)
//                            )
//                        }
//                        else ->{
//                            CoinListScreen(
//                                state = state,
//                                modifier = Modifier.padding(innerPadding),
//                                onAction = viewModel::onAction
//                            )
//                        }
//                    }

                }
            }
        }
    }
}

