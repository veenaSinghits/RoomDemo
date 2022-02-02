package com.example.roomdemo

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.roomdemo.db.Subscriber
import com.example.roomdemo.db.SubscriberRepo
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repo: SubscriberRepo) :ViewModel(),Observable {

    val subscribers = repo.subscribers
    @Bindable
    val name = MutableLiveData<String?>()

    @Bindable
    val email = MutableLiveData<String?>()

    @Bindable
    val buttonSave = MutableLiveData<String>()

    @Bindable
    val buttonClear = MutableLiveData<String>()

    init {
        buttonSave.value = "Save"
        buttonClear.value ="Clear"
    }

    fun save(){
        val namesave :String = name.value!!
        val emailsave : String = email.value!!
        insert(Subscriber(0,namesave,emailsave))
        name.value = null
        email.value = null
    }

    fun clear(){
      deletAll()
    }

    fun insert(subscriber: Subscriber) :Job =
        viewModelScope.launch {
            repo.insert(subscriber)
        }
    fun update(subscriber: Subscriber) :Job =
        viewModelScope.launch {
            repo.update(subscriber)
        }
    fun delete(subscriber: Subscriber) :Job =
        viewModelScope.launch {
            repo.delete(subscriber)
        }
    fun deletAll() :Job =
        viewModelScope.launch {
            repo.deleteAll()
        }


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }


}