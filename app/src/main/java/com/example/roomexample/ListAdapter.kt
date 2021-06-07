package com.example.roomexample

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.roomexample.data.User
import kotlinx.android.synthetic.main.custom_row.view.*



class ListAdapter(context: Context) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    val context = context

    private var userList: ArrayList<User> = ArrayList()

    fun updateData(user: List<User>) {
        this.userList.clear()
        this.userList.addAll(user)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount() = userList.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var check = true

        @SuppressLint("SetTextI18n")
        fun bind(user: User) {
            view.id_txt.text = user.id.toString()
            view.firstName_txt.text = user.firstName
            view.lastName_txt.text = user.lastName
            view.age_txt.text = user.age.toString()

            view.custom_row_root.setOnClickListener {
                (view.context as MainActivity).startUpdateSheet(user)
            }

            view.custom_row_root.setOnLongClickListener {
                (view.context as MainActivity).startDeleteDialog(user)
                true
            }
        }
    }
}
