package cn.yonghuige.testjson.avtivitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.yonghuige.testjson.R;
import cn.yonghuige.testjson.model.TCs;
import cn.yonghuige.testjson.utils.HttpUtils;
import cn.yonghuige.testjson.utils.Server_IPAddress;
import cn.yonghuige.testjson.utils.User_Info;

public class OrderActivity extends AppCompatActivity {

    TextView tv_order_course;
    TextView tv_order_teacher;
    TextView tv_order_count;
    EditText et_order_detail;
    Button btn_order;

    public static final String OrderAPI = Server_IPAddress.IP + "/set_order_answer.php";
    private Handler setOrderHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonData = (String) msg.obj;
            System.out.println(jsonData);

            try {
                JSONObject object1 = new JSONObject(jsonData);
                JSONObject object = new JSONObject(object1.getString("0"));
                int set_stat = object.getInt("stat");
                String order_action = object.getString("action");
                System.out.println("set_stat = " + set_stat);
                System.out.println("order_action = " + order_action);

                Toast.makeText(getApplicationContext(),order_action,Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        tv_order_course = (TextView)findViewById(R.id.tv_order_course_name);
        tv_order_teacher = (TextView)findViewById(R.id.tv_order_teacher_name);
        tv_order_count = (TextView)findViewById(R.id.tv_order_count);
        et_order_detail = (EditText)findViewById(R.id.et_order_detail);
        btn_order = (Button)findViewById(R.id.btn_order);

        final Bundle bundle = this.getIntent().getExtras();
        tv_order_teacher.setText(bundle.getString("TC_Teacher_Name"));
        tv_order_course.setText(bundle.getString("TC_These_Course"));
        tv_order_count.setText(bundle.getString("TC_Order_Count"));

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String set_Order_API = OrderAPI +
                        "?wait_answer_id=" + bundle.getString("TC_id") +
                        "&user_id=" + User_Info.getUser_id() +
                        "&others=" + et_order_detail.getText().toString();
                Log.e("OrderActivity:Order_API",set_Order_API);
                HttpUtils.setOrder(set_Order_API,setOrderHandler);

            }
        });




    }

}
