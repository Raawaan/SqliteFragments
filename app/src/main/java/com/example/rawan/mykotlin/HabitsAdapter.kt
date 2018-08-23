package com.example.rawan.mykotlin

import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.content.view.*
import kotlinx.android.synthetic.main.create_habit.view.*
import java.util.*
import kotlin.math.log

/**
 * Created by rawan on 8/14/18.
 */
class HabitsAdapter(val habitsList: List<DataClass>) : RecyclerView.Adapter<HabitsAdapter.HabitesView>() {

    class HabitesView(val v: View) : RecyclerView.ViewHolder(v)



    override fun onBindViewHolder(holder: HabitesView?, position: Int) {
        if (holder != null) {
            val habit = habitsList[position]
            holder.v.tvTitle.text = habit.title
            holder.v.tvDescription.text = habit.description
            holder.v.icon.setImageBitmap(habit.image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitesView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content, parent, false)
        return HabitesView(view)
    }
    override fun getItemCount() = habitsList.size

}
