package com.inekhaev.android.cryptoinvestor.domain.repository

import com.inekhaev.android.cryptoinvestor.data.remote.dto.CoinDetailDto
import com.inekhaev.android.cryptoinvestor.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}