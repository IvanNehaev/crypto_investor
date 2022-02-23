package com.inekhaev.android.cryptoinvestor.presentation.coin_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inekhaev.android.cryptoinvestor.common.Resource.Loading
import com.inekhaev.android.cryptoinvestor.common.Resource.Success
import com.inekhaev.android.cryptoinvestor.common.Resource.Error
import com.inekhaev.android.cryptoinvestor.domain.model.Coin
import com.inekhaev.android.cryptoinvestor.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

//    private val _state = mutableStateOf<CoinListState>(CoinListState())
//    val state: State<CoinListState> = _state

    private val _state = MutableLiveData<CoinListState>()
    val state: LiveData<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when(result) {
                is Success -> {
                    _state.value = CoinListState.Success(coins = result.data ?: emptyList())
                }
                is Error -> {
                    _state.value = CoinListState.Error(
                        errorText = result.message ?: "An expected error occured")
                }
                is Loading -> {
                    _state.value = CoinListState.Loading()
                }
            }
        }.launchIn(viewModelScope)
    }
}