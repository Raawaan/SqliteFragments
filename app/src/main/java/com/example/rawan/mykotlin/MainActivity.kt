package com.example.rawan.mykotlin

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.rawan.mykotlin.db.HabitDbTable
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.widget.SwipeRefreshLayout
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    companion object {
        var index:Int=1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fun update(){
            newRV.layoutManager = LinearLayoutManager(this)
            var adp =HabitsAdapter(HabitDbTable(this).ReadAllData())
            newRV.adapter = adp
       }
        refreshaa.setOnRefreshListener({
            refreshaa.setRefreshing(false)
            update()
        })
        supportFragmentManager.beginTransaction().add(R.id.container,FragmentRV.newInstance(),"a").commit()
        showHide.setOnClickListener {
            if (index==1){
                update()
                newRV.visibility=View.VISIBLE
                index=2
                val fragment = CreateHabit.newInstance()
                replaceFragments(fragment)
            }
            else if(index==2) {
                newRV.visibility=View.GONE
                index=1
                val fragment = FragmentRV.newInstance()
                replaceFragments(fragment)
            }
        }
    }

//    override fun onConfigurationChanged(newConfig: Configuration?) {
//        super.onConfigurationChanged(newConfig)
//        if(newConfig?.orientation==Configuration.ORIENTATION_PORTRAIT){
//            Toast.makeText(this,"ORIENTATION_PORTRAIT", Toast.LENGTH_SHORT).show()
////            newRV.visibility=View.VISIBLE
//
//        }    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.addHabit) {
            val intent = Intent(this, CreateHabit::class.java)
            startActivity(intent)
        }
        return true
    }
    private fun replaceFragments(fragment: Fragment){
        val fragmentTransaction= supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container,fragment).commit()
    }

}
