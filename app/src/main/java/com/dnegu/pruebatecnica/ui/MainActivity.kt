package com.dnegu.pruebatecnica.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.dnegu.pruebatecnica.R
import com.dnegu.pruebatecnica.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        enableEdgeToEdge()
    }

    fun showLoading(isShowing: Boolean, text: String = getString(R.string.main_activity_loading)){
        if(isShowing){
            binding.clProgressBar.visibility = View.VISIBLE
            binding.tvProgressBar.text = text
        } else {
            binding.clProgressBar.visibility = View.GONE
        }
    }
}