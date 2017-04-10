package cn.yonghuige.testjson.avtivitys;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.yonghuige.testjson.R;
import cn.yonghuige.testjson.model.TCs;
import cn.yonghuige.testjson.utils.HttpUtils;
import cn.yonghuige.testjson.utils.Server_IPAddress;
import cn.yonghuige.testjson.utils.User_Info;

public class TC_Detail extends AppCompatActivity {
    TextView td_tv_id;
    TextView td_tvTeacher_name;
    TextView td_tvThese_course;
    TextView td_tvAnswer_time;
    TextView td_tvOthers;
    TextView td_tvorder_count;
    Button btn_td_order;
    String order_count;



    private Handler getOrderCountHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonData = (String) msg.obj;
            System.out.println(jsonData);
            try {
                JSONObject jsonObject = new JSONObject(jsonData);

                    JSONObject object = jsonObject.getJSONObject("0");
                    order_count = object.getString("order_count");
                    System.out.println("order_count = " + order_count);
                td_tvorder_count.setText(order_count);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tc__detail);
        td_tv_id = (TextView)findViewById(R.id.td_tc_id);
        td_tvTeacher_name = (TextView) findViewById(R.id.td_tc_teacher_name);
        td_tvThese_course = (TextView) findViewById(R.id.td_tc_these_courses);
        td_tvAnswer_time = (TextView) findViewById(R.id.td_tc_answer_time);
        td_tvOthers = (TextView) findViewById(R.id.td_tc_others);
        btn_td_order = (Button) findViewById(R.id.btn_tc_to_order);
        td_tvorder_count = (TextView) findViewById(R.id.td_tc_order_num);

        final Bundle bundle = this.getIntent().getExtras();
        String getCountOrderAPI = Server_IPAddress.IP + "/getCountOrder.php" + "?wait_answer_id=" + bundle.getString("TC_Id");
        HttpUtils.getOrderCount(getCountOrderAPI,getOrderCountHandler);
        td_tv_id.setText(bundle.getString("TC_Id"));
        td_tvTeacher_name.setText(bundle.getString("TC_Teacher_Name"));
        td_tvThese_course.setText(bundle.getString("TC_These_Course"));
        td_tvAnswer_time.setText(bundle.getString("TC_Answer_Time"));
        td_tvOthers.setText(bundle.getString("TC_Others"));


        btn_td_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(User_Info.user_type == 2){
                Intent intent = new Intent();
                intent.setClass(TC_Detail.this,OrderActivity.class);
                intent.putExtra("TC_id",bundle.getString("TC_Id"));
                intent.putExtra("TC_Teacher_Name",bundle.getString("TC_Teacher_Name"));
                intent.putExtra("TC_These_Course",bundle.getString("TC_These_Course"));
                intent.putExtra("TC_Answer_Time",bundle.getString("TC_Answer_Time"));
                intent.putExtra("TC_Others",bundle.getString("TC_Others"));
                intent.putExtra("TC_Order_Count",order_count);
                startActivity(intent);
                }else if(User_Info.user_type == 1){
                    Toast.makeText(TC_Detail.this, "抱歉！您是教师，没有预约答疑的权限！", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(TC_Detail.this, "无效的用户！", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
