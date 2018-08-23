package com.example.rawan.mykotlin

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rawan.mykotlin.db.HabitDbTable
import kotlinx.android.synthetic.main.fragment_recycler_view.*

/**
 * Created by rawan on 8/16/18.
 */
class FragmentRV: android.support.v4.app.Fragment(){

override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?): View? {

    return inflater.inflate(R.layout.fragment_recycler_view, container, false)

}
companion object {
    fun newInstance():FragmentRV {
        return FragmentRV()
    }
}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myRVItem.setHasFixedSize(true)
        myRVItem.layoutManager = LinearLayoutManager(activity)
        myRVItem.adapter = HabitsAdapter(HabitDbTable(this.context).ReadAllData())

    }
}