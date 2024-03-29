package com.kok.grade_calculator

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray
import org.json.JSONException


class MySharedPreferences(context: Context) {

    val PREFS_FILENAME = "prefs"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)
    /* 파일 이름과 EditText를 저장할 Key 값을 만들고 prefs 인스턴스 초기화 */

    val PREF_GRADUATE = "Graduate"
    val PREF_MAJOR_GRADUATE= "majorGraduate"
    val PREF_USER_GOAL = "userGoal"
    val FLAG = "signIn_FLAG"

    val PREF_TOTAL_GPA = "totalGPA"
    val PREF_TOTAL_CREDIT = "totalCredit"           //전체 과목 이수 학점
    val PREF_TOTAL_CREDIT_PF = "totalCredit_PF"     //패논패 과목 이수 학점
    val PREF_TOTAL_RETAKE_GPA = "retakeGPA"


    fun setStringArrayPref(
        key: String,
        values: ArrayList<String>
    ) {
        val editor = prefs.edit()
        val a = JSONArray()
        for (i in 0 until values.size) {
            a.put(values[i])
        }
        if (values.isNotEmpty()) {
            editor.putString(key, a.toString())
        } else {
            editor.putString(key, null)
        }
        editor.apply()
    }

    fun getStringArrayPref(key: String):ArrayList<String>{
        val json = prefs.getString(key,null)
        val urls = ArrayList<String>()
        if(json != null){
            try {
                val a = JSONArray(json)
                for(i in 0 until a.length()){
                    val url = a.optString(i)
                    urls.add(url)
                }
            }catch (e: JSONException){
                e.printStackTrace()
            }
        }
        return urls
    }


    // 계정 정보 저장
    fun setUserInfo(
        graduate: Int,
        majorGraduate: Int,
        userGoal:Float
    ) {
        val editor = prefs.edit()
        editor.putInt(PREF_GRADUATE, graduate)
        editor.putInt(PREF_MAJOR_GRADUATE, majorGraduate)
        editor.putFloat(PREF_USER_GOAL, userGoal)
        editor.putBoolean(FLAG,true)
        editor.apply()
    }

    fun setTotalGPA(totalGPA: Float){
        val editor = prefs.edit()
        editor.putFloat(PREF_TOTAL_GPA, totalGPA)

        editor.apply()
    }

    fun setTotalCredit(totalCredit: Int){
        val editor = prefs.edit()
        editor.putInt(PREF_TOTAL_CREDIT, totalCredit)
        editor.apply()
    }

    fun setTotalCreditPF(totalCreditPF: Int){
        val editor = prefs.edit()
        editor.putInt(PREF_TOTAL_CREDIT_PF, totalCreditPF)
        editor.apply()
    }

    fun setRetakeGPA(retakeGPA: Float) {
        val editor = prefs.edit()
        editor.putFloat(PREF_TOTAL_RETAKE_GPA, retakeGPA)
        editor.apply()
    }

    fun updateCredit(totalCredit: Int, totalCreditPF: Int) {
        val editor = prefs.edit()
        editor.putInt(PREF_TOTAL_CREDIT, totalCredit)
        editor.putInt(PREF_TOTAL_CREDIT_PF, totalCreditPF)
        editor.apply()
    }


    // 저장된 정보 가져오기
    fun getGraduate(): Int {
        return prefs.getInt(PREF_GRADUATE,0)
    }

    fun getMajorGraduate(): Int {
        return prefs.getInt(PREF_MAJOR_GRADUATE, 0)
    }

    fun getUserGoal(): Float {
        return prefs.getFloat(PREF_USER_GOAL, 0.toFloat())
    }

    fun getStartFlag():Boolean {
        return prefs.getBoolean(FLAG, false )
    }

    fun getTotalGPA():Float{
        return prefs.getFloat(PREF_TOTAL_GPA,0.toFloat())
    }

    fun getTotalCredit():Int{
        return prefs.getInt(PREF_TOTAL_CREDIT,0)
    }

    fun getTotalCreditPF():Int{
        return prefs.getInt(PREF_TOTAL_CREDIT_PF,0)
    }

    fun getRetakeGPA():Float {
        return prefs.getFloat(PREF_TOTAL_RETAKE_GPA, 0.toFloat())
    }

}