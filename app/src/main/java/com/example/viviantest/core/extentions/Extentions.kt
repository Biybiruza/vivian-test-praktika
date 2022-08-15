package com.example.viviantest.core.extentions

import android.view.View

fun View.visibility(visibility: Boolean): View {
    if (visibility) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
    return this
}

fun View.onClick(onClick: (View) -> Unit){
    this.setOnClickListener(onClick)
}