package com.inekhaev.android.cryptoinvestor.data.repository

import com.inekhaev.android.cryptoinvestor.data.remote.CoinPaprikaApi
import com.inekhaev.android.cryptoinvestor.data.remote.dto.CoinDetailDto
import com.inekhaev.android.cryptoinvestor.data.remote.dto.CoinDto
import com.inekhaev.android.cryptoinvestor.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}