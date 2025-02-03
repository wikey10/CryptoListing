package com.app.cryptolisting.core.domain_util.navigation

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.cryptolisting.crypto.presentation.coin_detail.CoinDetailScreen
import com.app.cryptolisting.crypto.presentation.coin_list.CoinListAction
import com.app.cryptolisting.crypto.presentation.coin_list.CoinListEvent
import com.app.cryptolisting.crypto.presentation.coin_list.CoinListScreen
import com.app.cryptolisting.crypto.presentation.coin_list.CoinListViewModel
import com.app.cryptolisting.crypto.presentation.coin_list.utils.ObserveAsEvents
import com.app.cryptolisting.crypto.presentation.coin_list.utils.toString
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptiveCoinListDetailPane(
    modifier: Modifier = Modifier,
    viewModel: CoinListViewModel = koinViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    ObserveAsEvents(events =  viewModel.events) {
            event -> when(event) {
        is CoinListEvent.Error -> {
            Toast.makeText(
                context,
                event.error.toString(context),
                Toast.LENGTH_LONG
            ).show()
        }
    }
    }

    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator =navigator,
        listPane =  {
            AnimatedPane {
                CoinListScreen(
                    state = state,
                    onAction = {action->
                        viewModel.onAction(action)
                        when(action){
                            is CoinListAction.OnClick -> {
                                navigator.navigateTo(
                                    pane = ListDetailPaneScaffoldRole.Detail
                                )
                            }
                        }

                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                CoinDetailScreen(
                    state = state,
                    modifier = modifier
                )
            }
        },

    )

}