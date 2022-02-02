package com.example.roomdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdemo.db.SubscriberRepo
import java.lang.IllegalArgumentException

class SubscriberviewModelFactory(private val repo: SubscriberRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubscriberViewModel::class.java)) {
            return SubscriberViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}