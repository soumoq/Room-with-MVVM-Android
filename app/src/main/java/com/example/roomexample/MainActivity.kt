package com.example.roomexample

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomexample.data.User
import com.example.roomexample.data.UserViewModel

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

    fun startUpdateSheet(user: User) {
        val updateBottomSheet = UpdateBottomSheet(user)
        updateBottomSheet.show(supportFragmentManager, "Fragment")
    }

    fun startDeleteDialog(user: User) {
        val alert = this?.let { it1 -> AlertDialog.Builder(it1) }
        alert?.setTitle("Are you sure you want to delete the User")
        alert?.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
            mUserViewModel.deleteUser(user)
        })
        alert?.setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, which ->
        })
        alert?.show()
    }

}