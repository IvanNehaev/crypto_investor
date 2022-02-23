package com.inekhaev.android.cryptoinvestor.presentation.coin_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inekhaev.android.cryptoinvestor.R
import com.inekhaev.android.cryptoinvestor.presentation.CoinAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CoinListFragment: Fragment(R.layout.fragment_coin_list) {

    private lateinit var mRvCoin: RecyclerView
    private lateinit var mCoinAdapter: CoinAdapter

    private val mViewModel: CoinListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(view)
        setupData()
    }

    private fun setupRecyclerView(view: View) {
        mRvCoin = view.findViewById(R.id.rv_coin_list)
        mCoinAdapter = CoinAdapter()
        mRvCoin.apply {
            adapter = mCoinAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupData() {
        mViewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CoinListState.Success -> {
                    mCoinAdapter.submitList(state.coins)
                }
                is CoinListState.Loading -> {

                }
                is CoinListState.Error -> {

                }
            }
        }
    }
}