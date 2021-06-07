package com.example.roomexample

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.data.User
import com.example.roomapp.data.UserViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.input_buttom_sheet.*
import kotlinx.android.synthetic.main.input_buttom_sheet.view.*

class InputBottomSheet : BottomSheetDialogFragment()  {

    var fastName : EditText? = null
    var lastName : EditText? = null
    var age_et : EditText? = null


    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.input_buttom_sheet, container, false)

        fastName = view.fast_name
        lastName = view.last_name
        age_et = view.age

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.insert.setOnClickListener {
            insertDataToDatabase()
        }

        return view;

    }

    private fun insertDataToDatabase() {
        val firstName = fastName?.text.toString()
        val lastName = lastName?.text.toString()
        val age = age_et?.text.toString()

        if(inputCheck(firstName, lastName, age)){
            // Create User Object
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            // Add Data to Database
            mUserViewModel.addUser(user)
            Toast.makeText(context, "Successfully added!", Toast.LENGTH_LONG).show()
            dismiss()
        }else{
            Toast.makeText(context, "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}