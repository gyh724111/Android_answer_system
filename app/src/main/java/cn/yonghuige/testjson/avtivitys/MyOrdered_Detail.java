package cn.yonghuige.testjson.avtivitys;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.yonghuige.testjson.R;
import cn.yonghuige.testjson.adapter.MOedAdapter;
import cn.yonghuige.testjson.adapter.MOedDetailAdapter;
import cn.yonghuige.testjson.model.MOed;
import cn.yonghuige.testjson.model.MOedDetail;
import cn.yonghuige.testjson.utils.HttpUtils;
import cn.yonghuige.testjson.utils.Server_IPAddress;
import cn.yonghuige.testjson.utils.User_Info;

public class MyOrdered_Detail extends AppCompatActivity {
    private ListView lvmoed_detail;
    private TextView tv_ordered_detail_title;
    private static MOedDetailAdapter adapter;
    private static List<MOedDetail> moedDetailsList;
    public static final String GET_MOedDetail_URL = Server_IPAddress.IP + "/getMyOrderedDetail.php?wait_answer_id=";


    private static Handler getMOedDetailHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonData = (String) msg.obj;
            System.out.println(jsonData);
            try {
                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String order_answer_id = object.getString("My_Ordered_id");
                    String wait_answer_id = object.getString("wait_answer_id");
                    String these_courses = object.getString("these_courses");
                    String answer_time = object.getString("answer_time");
                    String answer_position = object.getString("answer_position");
                    String others = object.getString("others");
                    String student_id = object.getString("stu_id");
                    String student_name = object.getString("stu_name");
                    moedDetailsList.add(new MOedDetail(order_answer_id,wait_answer_id,these_courses,answer_time,
                            answer_position,others,student_id, student_name));
                    System.out.println("My_Ordered_id = " + order_answer_id);
                    System.out.println("wait_answer_id = " + wait_answer_id);
                    System.out.println("these_courses = " + these_courses);
                    System.out.println("answer_time = " + answer_time);
                    System.out.println("answer_position = " + answer_position);
                    System.out.println("others = " + others);
                    System.out.println("student_id = " + student_id);
                    System.out.println("student_name = " + student_name);
                }
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    public static Handler refreshMOedDetailHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                moedDetailsList.clear();
                Log.e("rfMOedDetailHandler",(String)msg.obj);
                String GET_MOedsDetail_URL2 = GET_MOedDetail_URL + User_Info.wait_answer_id;
                HttpUtils.getOrderedDetail(GET_MOedsDetail_URL2, getMOedDetailHandler);
                adapter.notifyDataSetChanged();
            }catch (Exception e) {
                e.printStackTrace();
            }


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ordered__detail);

        tv_ordered_detail_title = (TextView)findViewById(R.id.tv_MODetail_title);
        lvmoed_detail = (ListView) findViewById(R.id.lv_MOed_Detail);
        moedDetailsList = new ArrayList<MOedDetail>();
        adapter = new MOedDetailAdapter(this, moedDetailsList);
        lvmoed_detail.setAdapter(adapter);

        final Bundle bundle = this.getIntent().getExtras();
        tv_ordered_detail_title.setText(bundle.getString("These_Course"));
        User_Info.setWait_answer_id(bundle.getString("wait_answer_id"));
        String GET_MOedsDetail_URL = GET_MOedDetail_URL + bundle.getString("wait_answer_id");
        Log.e("GET_MOedsDetail_URL",GET_MOedsDetail_URL);
        HttpUtils.getOrderedDetail(GET_MOedsDetail_URL, getMOedDetailHandler);

    }

}
