package com.fmstudio.tourandtravelplanner.helper

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken


object SessionManager {


    private val PREF_NAME: String = "TourAndTravel.pref"
    lateinit var preferences: SharedPreferences

    fun with(application: Application) {
        preferences = application.getSharedPreferences(PREF_NAME , Context.MODE_PRIVATE)
    }

    fun <T> setObject(`object`: T, key: String) {
        val jsonString = GsonBuilder().create().toJson(`object`)
        preferences.edit().putString(key, jsonString).apply()
    }

    inline fun <reified T> getObject(key: String): T? {
        val value = preferences.getString(key, null)
        val type = object : TypeToken<T>() {}.type
        return GsonBuilder().create().fromJson(value, type)
    }

    fun setString(key: String, value:String){
        preferences.edit().putString(key, value).apply()
    }

    fun getString(key: String, defVal: String):String{
        return preferences.getString(key, defVal).toString()
    }

    fun putBool(key: String, value:Boolean){
        preferences.edit().putBoolean(key, value).apply()
    }

    fun getBool(key: String, defVal:Boolean):Boolean{
        return preferences.getBoolean(key, defVal)
    }

    fun setInt(key: String, value:Int){
        preferences.edit().putInt(key, value).apply()
    }

    fun getInt(key: String, defVal: Int=0):Int{
        return preferences.getInt(key, defVal)
    }

    fun putSets(key: String, value:Set<String>){
        preferences.edit().putStringSet(key, value).apply()
    }

    fun getSets(key: String):MutableSet<String>{
        return preferences.getStringSet(key, mutableSetOf()) ?: mutableSetOf()
    }

    const val KEY_SELECTED_CITY = "key_selected_city"
    const val KEY_CHECK_IN_DATE = "key_check_in_date"
    const val KEY_CHECK_OUT_DATE = "key_check_out_date"
    const val KEY_ROOMS = "key_rooms"
    const val KEY_ADULTS = "key_adults"
    const val KEY_CHILDREN = "key_children"
    const val KEY_BABIES = "key_babies"
    const val KEY_LOCALE_LANGUAGE = "flash.locale.language"
    const val KEY_USER_NAME = "user_name"
    const val KEY_USER_EMAIL = "user_email"
    const val KEY_USER_PHONE = "user_phone"





}