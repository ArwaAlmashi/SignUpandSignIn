package com.example.mynoteapp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.signupandsignin.model.User

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "notes.db", null, 1) {

    private var sqLiteDatabase: SQLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table users (Email text PRIMARY KEY ,Name text, Phone text, Password text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}


    // ADD
    fun saveNotes(user: User) {
        val contentValues = ContentValues()
        contentValues.put("Email", user.email)
        contentValues.put("Name", user.name)
        contentValues.put("Phone", user.phone)
        contentValues.put("Password", user.password)
        sqLiteDatabase.insert("users", null, contentValues)
    }

    // READ
    fun readAllUsers(): ArrayList<User> {

        val users = arrayListOf<User>()
        val cursor: Cursor = sqLiteDatabase.rawQuery("SELECT * FROM users", null)

        if (cursor.count < 1) {
            println("No Data Found")
        } else {
            while (cursor.moveToNext()) {

                // Read data
                val email = cursor.getString(0)
                val name = cursor.getString(1)
                val phone = cursor.getString(2)
                val password = cursor.getString(3)

                users.add(User(name, email, phone, password))

            }
        }

        println()
        return users
    }

    fun getUser(userEmail: String): User {

        var user : User = User("","","","")
        val cursor: Cursor = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE Email LIKE '$userEmail'", null)

        if (cursor.count < 1) {
            println("No Data Found")
        } else {
            while (cursor.moveToNext()) {

                // Read data
                val email = cursor.getString(0)
                val name = cursor.getString(1)
                val phone = cursor.getString(2)
                val password = cursor.getString(3)

                user = User(name, email, phone, password)

            }
        }

        return user
    }



}