package com.dnegu.pruebatecnica.ui.features.imagecircular

import android.graphics.Color
import com.dnegu.pruebatecnica.common.BaseFragment
import com.dnegu.pruebatecnica.databinding.FragmentImageCircularBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageCircularFragment : BaseFragment<FragmentImageCircularBinding,ImageCircularViewModel>() {

    override fun getViewModelClass() = ImageCircularViewModel::class.java
    override fun getViewBinding() = FragmentImageCircularBinding.inflate(layoutInflater)

    override fun setUpViews() {
        super.setUpViews()

        binding.circularImageView.loadImage("https://img.freepik.com/foto-gratis/mujer-hermosa-joven-mirando-camara-chica-moda-verano-casual-camiseta-blanca-pantalones-cortos-hembra-positiva-muestra-emociones-faciales-modelo-divertido-aislado-amarillo_158538-15796.jpg", "John Doe")
        binding.circularImageView2.loadImage("", "John Doe", textColor = Color.WHITE, backgroundColor =  Color.RED)
        binding.circularImageView4.loadImage("", "John")
        binding.circularImageView3.loadImage("", "")
    }
}