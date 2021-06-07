package com.example.roomexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.data.UserViewModel

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            val inputBottomSheet = InputBottomSheet()
            inputBottomSheet.show(supportFragmentManager, "Fragment")
        }

        val listAdapter = ListAdapter(this)
        user_list.adapter = listAdapter

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(this, Observer {
            listAdapter.updateData(it)
        })



    }
}