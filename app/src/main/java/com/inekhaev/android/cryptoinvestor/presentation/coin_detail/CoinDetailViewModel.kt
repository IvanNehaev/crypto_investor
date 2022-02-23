package com.inekhaev.android.cryptoinvestor.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inekhaev.android.cryptoinvestor.common.Constants
import com.inekhaev.android.cryptoinvestor.common.Resource.Loading
import com.inekhaev.android.cryptoinvestor.common.Resource.Success
import com.inekhaev.android.cryptoinvestor.common.Resource.Error
import com.inekhaev.android.cryptoinvestor.domain.use_case.get_coin.GetCoinUseCase
import com.inekhaev.android.cryptoinvestor.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when(result) {
                is Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
                is Error -> {
                    _state.value = CoinDetailState(
                        error = result.message ?: "An expected error occured")
                }
                is Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}