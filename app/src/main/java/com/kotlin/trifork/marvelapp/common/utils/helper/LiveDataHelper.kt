package com.kotlin.trifork.marvelapp.common.utils.helper

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<T>.subscribe(
    lifecycleOwner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
) {
    observe(lifecycleOwner, Observer { it?.run(onChanged) })
}