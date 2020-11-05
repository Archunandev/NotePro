package com.example.techsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var dbHelper: MyDbHelper
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = MyDbHelper(this,null,null,1)
        viewWorkers()

        floatingActionButton.setOnClickListener {
            val i = Intent (this,AddWorkers::class.java)
            startActivity(i)
        }




    }

    private fun viewWorkers(){
        val workersList = dbHelper.getWorkers(this)
        val adapter = WorkersAdapter(this,workersList)
        val rv : RecyclerView = findViewById(R.id.recyclerView)
        rv.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager
        rv.adapter = adapter
        


    }

    override fun onResume() {

        viewWorkers()
        super.onResume()
    }
}