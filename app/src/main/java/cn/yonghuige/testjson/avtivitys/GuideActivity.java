package cn.yonghuige.testjson.avtivitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.yonghuige.testjson.R;
import cn.yonghuige.testjson.utils.User_Info;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_to_tc;
    Button btn_to_rp;
    Button btn_to_mo;
    Button btn_to_moed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        btn_to_tc = (Button) findViewById(R.id.btn_to_tc);
        btn_to_rp = (Button) findViewById(R.id.btn_to_rp);
        btn_to_mo = (Button) findViewById(R.id.btn_to_mo);
        btn_to_moed = (Button) findViewById(R.id.btn_to_moed);
        btn_to_tc.setOnClickListener(this);
        btn_to_rp.setOnClickListener(this);
        btn_to_mo.setOnClickListener(this);
        btn_to_moed.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_to_tc:
                Log.e("GuideActivity", "TO TC");
                Intent intent1 = new Intent();
                intent1.setClass(GuideActivity.this, TC_Search.class);
                startActivity(intent1);
                break;
            case R.id.btn_to_rp:
                Log.e("GuideActivity", "TO RP");
                Intent intent2 = new Intent();
                intent2.setClass(GuideActivity.this, All_RP_Search.class);
                startActivity(intent2);
                break;
            case R.id.btn_to_mo:
                Log.e("GuideActivity", "TO MO");
                if (User_Info.user_type == 2) {
                    Intent intent3 = new Intent();
                    intent3.setClass(GuideActivity.this, MyOrdersActivity.class);
                    startActivity(intent3);
                } else if (User_Info.user_type == 1) {
                    Toast.makeText(GuideActivity.this, "您是教师，没有预约模块的使用权限!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GuideActivity.this, "无效的用户!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_to_moed:
                Log.e("GuideActivity", "TO MOed");
                if (User_Info.user_type == 1) {
                    Intent intent4 = new Intent();
                    intent4.setClass(GuideActivity.this, MyOrderedActivity.class);
                    startActivity(intent4);
                } else if (User_Info.user_type == 2) {
                    Toast.makeText(GuideActivity.this, "您是学生，没有该模块的使用权限!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GuideActivity.this, "无效的用户!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
