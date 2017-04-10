package cn.yonghuige.testjson.utils;

import android.util.Log;

/**
 * Created by gyh on 2017/3/16.
 */

public class User_Info {

    public static String user_id = "";
    public static String username = "";
    public static String password = "";
    public static int user_type = 0;
    public static String wait_answer_id = "";

    public static String getWait_answer_id() {
        return wait_answer_id;
    }

    public static void setWait_answer_id(String wait_answer_id) {
        User_Info.wait_answer_id = wait_answer_id;
    }

    public static int getUser_type() {
        return user_type;
    }

    public static String getUser_id() {
        return user_id;
    }

    public static void setUser_id(String user_id) {
        User_Info.user_id = user_id;
        Log.e("User_Info","setUser_id = " + user_id);
    }

    public static void setUser_type(int user_type) {
        User_Info.user_type = user_type;
        Log.e("User_Info","setUser_type = " + user_type);
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User_Info.password = password;
        Log.e("User_Info","setPassword = " + password);
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        User_Info.username = username;
        Log.e("User_Info","setUsername = " + username);
    }



}
