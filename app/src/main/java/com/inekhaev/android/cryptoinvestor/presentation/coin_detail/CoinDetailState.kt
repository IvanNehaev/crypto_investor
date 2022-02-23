package com.inekhaev.android.cryptoinvestor.presentation.coin_detail

import com.inekhaev.android.cryptoinvestor.domain.model.Coin
import com.inekhaev.android.cryptoinvestor.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
