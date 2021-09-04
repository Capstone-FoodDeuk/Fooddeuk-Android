package com.seoultech.fooddeuk.login

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.seoultech.fooddeuk.R

object LoginBindingAdapter {
    @BindingAdapter("setLoginLogo")
    fun setLoginLogo(view: ImageView, userType: String) {
        if (userType == UserType.CEO.name) {
            view.setImageResource(R.drawable.ic_ceo_login_logo)
        } else if (userType == UserType.CUSTOMER.name) {
            view.setImageResource(R.drawable.ic_customer_login_logo)
        }
    }
}