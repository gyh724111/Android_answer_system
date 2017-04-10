package cn.yonghuige.testjson.avtivitys;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.yonghuige.testjson.R;
import cn.yonghuige.testjson.adapter.MOAdapter;
import cn.yonghuige.testjson.adapter.TCAdapter;
import cn.yonghuige.testjson.model.MOs;
import cn.yonghuige.testjson.model.TCs;
import cn.yonghuige.testjson.utils.HttpUtils;
import cn.yonghuige.testjson.utils.Server_IPAddress;
import cn.yonghuige.testjson.utils.User_Info;

public class MyOrdersActivity extends AppCompatActivity {

    private ListView lvmo;
    private static MOAdapter adapter;
    private static List<MOs> MOList;
    public static final String GET_MOs_URL = Server_IPAddress.IP + "/getmyorders.php";
    public static String  Get_MyOrder_URL = GET_MOs_URL + "?user_id=" + User_Info.getUser_id();

    public static Handler getMOsHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonData = (String) msg.obj;
            System.out.println(jsonData);
            try {
                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    int id = object.getInt("MOs_id");
                    String these_courses = object.getString("these_courses");
                    String teacher_name = object.getString("teacher_name");
                    String others = object.getString("others");
                    MOList.add(new MOs(these_courses,teacher_name, others,id));
                    System.out.println("id = " + id);
                    System.out.println("these_courses = " + these_courses);
                    System.out.println("teacher_name = " + teacher_name);
                    System.out.println("others = " + others);
                }
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    public static Handler refreshMOsHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                MOList.clear();
                Log.e("refreshMOsHandler",(String)msg.obj);
                HttpUtils.getMOJSON(Get_MyOrder_URL, getMOsHandler);
                adapter.notifyDataSetChanged();
            }catch (Exception e) {
                e.printStackTrace();
            }


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        //String Get_MyOrder_URL = GET_MOs_URL + "?user_id=" + User_Info.getUser_id();
        lvmo = (ListView)findViewById(R.id.lvMOs);
        MOList = new ArrayList<MOs>();
        adapter = new MOAdapter(this, MOList);
        lvmo.setAdapter(adapter);
        HttpUtils.getMOJSON(Get_MyOrder_URL, getMOsHandler);

    }
}
