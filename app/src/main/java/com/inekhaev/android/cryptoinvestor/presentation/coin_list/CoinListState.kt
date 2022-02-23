package com.inekhaev.android.cryptoinvestor.presentation.coin_list

import com.inekhaev.android.cryptoinvestor.domain.model.Coin

sealed class CoinListState(val coins: List<Coin> = emptyList(), val message: String? = null) {
    class Success(coins: List<Coin>) : CoinListState(coins)
    class Error(errorText: String) : CoinListState(message = errorText)
    class Loading() : CoinListState()
}
