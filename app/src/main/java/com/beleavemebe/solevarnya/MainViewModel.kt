package com.beleavemebe.solevarnya

import androidx.annotation.IdRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val valuesForTextViews = mutableMapOf<Int, String>()

    fun fetchValueForTextView(@IdRes id: Int): String? =
        valuesForTextViews[id]

    fun notifyValueWasSetForTextView(@IdRes id: Int, value: String) {
        valuesForTextViews[id] = value
    }

    private val mutableClickCounter = MutableLiveData<Int>(0)
    val clickCounter: LiveData<Int> = mutableClickCounter
    fun onViewClicked() {
        mutableClickCounter.value = mutableClickCounter.value?.plus(1)
    }

    private val mutableCharacterCounter = MutableLiveData<Int>(0)
    val characterCounter: LiveData<Int> = mutableCharacterCounter
    fun onCharacterEntered() {
        mutableCharacterCounter.value = mutableCharacterCounter.value?.plus(1)
    }
}
