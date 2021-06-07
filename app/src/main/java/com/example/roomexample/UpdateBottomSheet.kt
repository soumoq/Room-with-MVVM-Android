package com.example.roomexample

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.roomexample.data.User
import com.example.roomexample.data.UserViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.input_buttom_sheet.view.*

class UpdateBottomSheet(user: User) : BottomSheetDialogFragment() {

    var fastName: EditText? = null
    var lastName: EditText? = null
    var age_et: EditText? = null

    var user = user

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.input_buttom_sheet, container, false)

        fastName = view.fast_name
        lastName = view.last_name
        age_et = view.age

        view.fast_name.setText(user.firstName)
        view.last_name.setText(user.lastName)
        view.age.setText(user.age.toString())

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.insert.text = "Update"
        view.insert.setOnClickListener {
            updateDataToDatabase()
        }

        return view;

    }

    private fun updateDataToDatabase() {
        val firstName = fastName?.text.toString()
        val lastName = lastName?.text.toString()
        val age = age_et?.text.toString()

        if (inputCheck(firstName, lastName, age)) {
            // Create User Object
            val updateUser = User(user.id, firstName, lastName, Integer.parseInt(age))
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_LONG).show()
            dismiss()
        } else {
            Toast.makeText(context, "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}