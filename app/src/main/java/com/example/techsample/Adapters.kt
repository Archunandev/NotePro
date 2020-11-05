package com.example.techsample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.lo_workers.view.*

class WorkersAdapter(mCtx : Context, val workers : ArrayList<Workers>) : RecyclerView.Adapter<WorkersAdapter.ViewHolder>(){

    val mCtx = mCtx

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val txtworkersname = itemView.workersname
        val txtworkersage = itemView.maxage
        val btnupdate = itemView.update
        val btndelete = itemView.delete
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkersAdapter.ViewHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.lo_workers,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return workers.size

    }

    override fun onBindViewHolder(holder: WorkersAdapter.ViewHolder, position: Int) {
        val worker : Workers = workers[position]
        holder.txtworkersname.text = worker.workersName
        holder.txtworkersage.text = worker.maxCredit.toString()


    }

}

