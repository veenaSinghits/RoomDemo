package com.example.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.databinding.ActivityMainBinding
import com.example.roomdemo.db.Subscriber
import com.example.roomdemo.db.SubscriberDAO
import com.example.roomdemo.db.SubscriberDb
import com.example.roomdemo.db.SubscriberRepo
import java.util.concurrent.Flow

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var adapter:MyRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao : SubscriberDAO = SubscriberDb.getInstance(application).subscriberDAO
        val repo = SubscriberRepo(dao)
        val factory = SubscriberviewModelFactory(repo)
        subscriberViewModel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        binding.subscriberview = subscriberViewModel
        binding.lifecycleOwner=this
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.recycler.layoutManager = LinearLayoutManager(this)
        adapter = MyRecyclerViewAdapter({selectedItem: Subscriber ->listItemClicked(selectedItem)})
        binding.recycler.adapter = adapter
        displayList()
    }

    private fun displayList(){
        subscriberViewModel.subscribers.observe(this, Observer {
            Log.d("Veena","List" + it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })


    }
    private fun listItemClicked(subscriber: Subscriber){
        Toast.makeText(this,"selected name is ${subscriber.name}", Toast.LENGTH_LONG).show()
    }
}