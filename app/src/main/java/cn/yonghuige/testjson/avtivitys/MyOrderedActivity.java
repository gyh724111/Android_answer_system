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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.yonghuige.testjson.R;
import cn.yonghuige.testjson.adapter.MOedAdapter;
import cn.yonghuige.testjson.model.MOed;
import cn.yonghuige.testjson.utils.HttpUtils;
import cn.yonghuige.testjson.utils.Server_IPAddress;
import cn.yonghuige.testjson.utils.User_Info;


public class MyOrderedActivity extends AppCompatActivity {
    private ListView lvmoed;
    private MOedAdapter adapter;
    private List<MOed> moedList;
    public static final String GET_MOed_URL = Server_IPAddress.IP + "/getMyOrdered.php?teacher_id=" + User_Info.user_id;

    private Handler getMOedHandler = new Handler() {
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
                    int count = object.getInt("count");
                    moedList.add(new MOed(order_answer_id,wait_answer_id,these_courses,answer_time, answer_position, others,count));
                    System.out.println("My_Ordered_id = " + order_answer_id);
                    System.out.println("wait_answer_id = " + wait_answer_id);
                    System.out.println("these_courses = " + these_courses);
                    System.out.println("answer_time = " + answer_time);
                    System.out.println("answer_position = " + answer_position);
                    System.out.println("others = " + others);
                    System.out.println("Count = " + order_answer_id);
                }
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ordered);


        lvmoed = (ListView) findViewById(R.id.lvMOed);
        moedList = new ArrayList<MOed>();
        adapter = new MOedAdapter(this, moedList);
        lvmoed.setAdapter(adapter);
        HttpUtils.getOrdered(GET_MOed_URL, getMOedHandler);
        Log.e("GET_MOed_URL",GET_MOed_URL);
        lvmoed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                System.out.println("these_courses = " + moedList.get(i).getThese_course());
                Intent intent = new Intent();
                intent.setClass(MyOrderedActivity.this, MyOrdered_Detail.class);
                intent.putExtra("My_Order_id", moedList.get(i).getMy_Ordered_id());
                intent.putExtra("wait_answer_id", moedList.get(i).getWait_answer_id());
                intent.putExtra("These_Course", moedList.get(i).getThese_course());
                intent.putExtra("Answer_Time", moedList.get(i).getAnswer_time());
                intent.putExtra("Answer_Position", moedList.get(i).getAnswer_time());
                intent.putExtra("Others", moedList.get(i).getOthers());
                intent.putExtra("count", moedList.get(i).getCount());
                startActivity(intent);
            }
        });

    }


}
