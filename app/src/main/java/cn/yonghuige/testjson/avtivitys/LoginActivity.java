package cn.yonghuige.testjson.avtivitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import cn.yonghuige.testjson.R;
import cn.yonghuige.testjson.utils.HttpUtils;
import cn.yonghuige.testjson.utils.Server_IPAddress;
import cn.yonghuige.testjson.utils.User_Info;

public class LoginActivity extends Activity {
    EditText et_userid;
    EditText et_password;
    Button btn_student_login;
    Button btn_teacher_login;

    String student_Login_Api;
    String teacher_Login_Api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText et = new EditText(this);
        new AlertDialog.Builder(this).setTitle("绑定服务器IP")
                .setIcon(android.R.drawable.btn_dialog)
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String input = et.getText().toString();
                        if (input.equals("")) {
                            Toast.makeText(getApplicationContext(), "服务器IP不能为空！" + input, Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "服务器IP为：" + input, Toast.LENGTH_LONG).show();

                            Server_IPAddress.IP = "http://" + input + "/answer_system";
                            Log.e("LoginActivity","Bind ServerIP = "+Server_IPAddress.IP);

                        }
                    }
                })
                .setNegativeButton("取消", null)
                .show();


        et_userid = (EditText) findViewById(R.id.et_login_username);
        et_password = (EditText) findViewById(R.id.et_login_password);
        btn_student_login = (Button) findViewById(R.id.btn_student_login);
        btn_teacher_login = (Button) findViewById(R.id.btn_teacher_login);


        btn_student_login.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String LoginAPI = Server_IPAddress.IP + "/LoginApi.php";
                student_Login_Api = LoginAPI + "?user_id=" + et_userid.getText().toString() + "&password=" + et_password.getText().toString() + "&user_type=2";
                Log.e("LoginActivity", "学生登录：请求地址为 = " + student_Login_Api);
                User_Info.setUser_type(2);
                HttpUtils.getLoginJSON(student_Login_Api, LoginHandler);
            }
        });

        btn_teacher_login.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                String LoginAPI = Server_IPAddress.IP + "/LoginApi.php";
                teacher_Login_Api = LoginAPI + "?user_id=" + et_userid.getText().toString() + "&password=" + et_password.getText().toString() + "&user_type=1";
                Log.e("LoginActivity", "教师登录：请求地址为 = " + teacher_Login_Api);
                User_Info.setUser_type(1);
                HttpUtils.getLoginJSON(teacher_Login_Api, LoginHandler);
            }
        });

    }

    private Handler LoginHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonData = (String) msg.obj;
            System.out.println(jsonData);
            try {
                JSONObject object1 = new JSONObject(jsonData);
                JSONObject object = new JSONObject(object1.getString("0"));
                int login_stat = object.getInt("stat");
                String login_final = object.getString("final");
                String username = object.getString("username");
                System.out.println("login_stat = " + login_stat);
                System.out.println("login_final = " + login_final);
                System.out.println("username = " + username);

                if (login_stat == 1) {
                    Log.e("LoginActivity", "login_stat = 1,登录失败");
                    Toast.makeText(getApplicationContext(),login_final,Toast.LENGTH_SHORT).show();
                } else if (login_stat == 0) {
                    Log.e("LoginActivity", "login_stat = 0,登录成功");
                    Toast.makeText(getApplicationContext(),login_final,Toast.LENGTH_SHORT).show();
                    User_Info.setUsername(username);
                    User_Info.setPassword(et_password.getText().toString());
                    User_Info.setUser_id(et_userid.getText().toString());

                    Log.e("LoginActivity","Toast end");
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this,GuideActivity.class);
                    startActivity(intent);

                }
                Toast.makeText(getApplicationContext(),login_final,Toast.LENGTH_SHORT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
