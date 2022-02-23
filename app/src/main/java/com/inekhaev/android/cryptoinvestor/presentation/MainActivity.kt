package com.inekhaev.android.cryptoinvestor.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.inekhaev.android.cryptoinvestor.R
import com.inekhaev.android.cryptoinvestor.presentation.coin_list.CoinListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<CoinListFragment>(R.id.main_fragment_container)
            }
        }
    }
}