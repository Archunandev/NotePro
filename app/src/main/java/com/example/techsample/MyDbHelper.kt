package com.example.techsample

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.lang.Exception

class MyDbHelper(context : Context, name : String?, factory:SQLiteDatabase.CursorFactory?, version : Int ) :

    SQLiteOpenHelper(context, DATABASE_NAME,factory, DATABSE_VERSION){



    companion object{
        private val DATABASE_NAME = "Workers.db"
        private val DATABSE_VERSION = 1

        val WORKERSTABLE_NAME = "WorkersList"
        val COLUMN_WORKERID = "Workersid"
        val COLUMN_WORKERNAME = "Workersname"
        val COLUMN_WORKERAGE = "Workersage"

    }

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_WORKER_TABLE = ("CREATE TABLE $WORKERSTABLE_NAME ("+
                "$COLUMN_WORKERID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "$COLUMN_WORKERNAME TEXT,"+
                "$COLUMN_WORKERAGE DOUBLE DEFAULT 0)")
        db?.execSQL(CREATE_WORKER_TABLE)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {






    }

    fun getWorkers(mCtx : Context ) : ArrayList<Workers> {
        val qry = "Select * From $WORKERSTABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        val workerss = ArrayList<Workers>()

        if (cursor.count == 0)
            Toast.makeText(mCtx, "No records", Toast.LENGTH_SHORT).show() else {
            while (cursor.moveToNext()) {
                val workers = Workers()
                workers.workersID = cursor.getInt(cursor.getColumnIndex(COLUMN_WORKERID))
                workers.workersName = cursor.getString(cursor.getColumnIndex(COLUMN_WORKERNAME))
                workers.maxCredit = cursor.getDouble(cursor.getColumnIndex(COLUMN_WORKERID))
                workerss.add(workers)

            }
            Toast.makeText(mCtx, "${cursor.count.toString()} Records Found", Toast.LENGTH_SHORT)
                .show()
        }
        cursor.close()
        db.close()
        return workerss
    }

    fun addWorkers(mCtx: Context, workers: Workers){
        val values = ContentValues()
        values.put(COLUMN_WORKERNAME,workers.workersName)
        values.put(COLUMN_WORKERAGE,workers.maxCredit)
        val db = this.writableDatabase
        try {
            db.insert(WORKERSTABLE_NAME ,null,values)
           // db.rawQuery("Insert Into $WORKERSTABLE_NAME ($COLUMN_WORKERNAME, $COLUMN_WORKERAGE) Values(?,?)")
            Toast.makeText(mCtx,"Workers Added",Toast.LENGTH_SHORT).show()

        }catch (e: Exception){
            Toast.makeText(mCtx,e.message,Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

}