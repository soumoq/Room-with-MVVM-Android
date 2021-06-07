package com.example.roomexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.roomapp.data.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.List.of

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val mUserViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        }catch (e : Exception){
            Log.e("ERRORR",e.message.toString())
        }


        fab.setOnClickListener {
            val inputBottomSheet = InputBottomSheet()
            inputBottomSheet.show(supportFragmentManager, "Fragment")
        }

    }
}