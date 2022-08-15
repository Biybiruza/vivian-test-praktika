package com.example.viviantest.settings

import android.content.Context
import android.content.SharedPreferences

class Settings(context: Context) {

    companion object {
        const val IS_APP_FIRST_LAUNCHED = "isAppFirstLaunched"
        const val BOOLEAN = "accessToken"
        const val USERNAME = "infoAddress"
        const val HEADER = "XMLHttpRequest"
        const val TOKEN = "notToken"
        const val USER_ROLE = "notRole"
        const val USER_NAME = "notUser"
        const val BRANCH = "notBranch"
        const val BRANCH_ID = "not id"
        const val PHONE_NAME = "notPhoneNumber"
        const val START_TIME = "notStartTime"
        const val END_TIME = "notEndTime"
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences("OneUpPreferences",Context.MODE_PRIVATE)

    var isFirstLaunched: Boolean
        set(value){preferences.edit().putBoolean(IS_APP_FIRST_LAUNCHED,value).apply()}
        get() = preferences.getBoolean(IS_APP_FIRST_LAUNCHED, true)

    var userId: Int
        set(value) {preferences.edit().putInt(USERNAME, value).apply()}
        get() = preferences.getInt(USERNAME, 0)

    var status: Boolean
        set(value) {preferences.edit().putBoolean(BOOLEAN,value).apply()}
        get() = preferences.getBoolean(BOOLEAN,false)

    var token: String
        set(value) {preferences.edit().putString(TOKEN, value).apply()}
        get() = preferences.getString(TOKEN, "") ?: ""

    var role: String
        set(value) {preferences.edit().putString(USER_ROLE,value).apply()}
        get() = preferences.getString(USER_ROLE, "") ?: ""

    var postName: String
        set(value) {preferences.edit().putString(USER_NAME, value).apply()}
        get() = preferences.getString(USER_NAME, "") ?: ""

    var branch: String
        set(value) {preferences.edit().putString(BRANCH, value).apply()}
        get() = preferences.getString(BRANCH, "") ?: ""

    var branchId: Int
        set(value) {preferences.edit().putInt(BRANCH_ID, value).apply()}
        get() = preferences.getInt(BRANCH_ID, 0)

    var phoneNumber: String
        set(value) {preferences.edit().putString(PHONE_NAME, value).apply()}
        get() = preferences.getString(PHONE_NAME, "") ?: ""

    var startTime: String
        set(value) {preferences.edit().putString(START_TIME, value).apply()}
        get() = preferences.getString(START_TIME, "") ?: ""

    var endTime: String
        set(value) {preferences.edit().putString(END_TIME, value).apply()}
        get() = preferences.getString(END_TIME, "") ?: ""
}