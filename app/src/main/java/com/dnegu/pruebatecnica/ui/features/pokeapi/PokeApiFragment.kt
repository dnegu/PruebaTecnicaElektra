package com.dnegu.pruebatecnica.ui.features.pokeapi

import com.dnegu.pruebatecnica.common.BaseFragment
import com.dnegu.pruebatecnica.databinding.FragmentPokeApiBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokeApiFragment : BaseFragment<FragmentPokeApiBinding,PokeApiViewModel>() {

    override fun getViewModelClass() = PokeApiViewModel::class.java
    override fun getViewBinding() = FragmentPokeApiBinding.inflate(layoutInflater)

    override fun setUpViews() {
        super.setUpViews()

    }
}