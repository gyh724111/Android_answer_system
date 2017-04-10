package cn.yonghuige.testjson.avtivitys;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.yonghuige.testjson.R;
import cn.yonghuige.testjson.adapter.TCAdapter;
import cn.yonghuige.testjson.model.TCs;
import cn.yonghuige.testjson.utils.HttpUtils;
import cn.yonghuige.testjson.utils.Server_IPAddress;

public class TC_Search extends AppCompatActivity {

    private Button btn_TCsearch;
    private EditText et_T_N;
    private EditText et_T_Cs;
    private ListView lvtc;
    private TCAdapter adapter;
    private List<TCs> TCList;
    public static final String GET_TCs_URL = Server_IPAddress.IP + "/getTCs.php";

    private Handler getTCsHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonData = (String) msg.obj;
            System.out.println(jsonData);
            try {
                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String id = object.getString("TCs_id");
                    String teacher_name = object.getString("teacher_name");
                    String these_courses = object.getString("these_courses");
                    String answer_time = object.getString("answer_time");
                    String others = object.getString("others");
                    TCList.add(new TCs(id, teacher_name, these_courses, answer_time, others));
                    System.out.println("id = " + id);
                    System.out.println("teacher_name = " + teacher_name);
                    System.out.println("these_courses = " + these_courses);
                    System.out.println("time = " + answer_time);
                    System.out.println("others = " + others);
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
        setContentView(R.layout.activity_tc_search);

        et_T_N = (EditText) findViewById(R.id.et_T_N);
        et_T_Cs = (EditText) findViewById(R.id.et_T_Cs);
        lvtc = (ListView) findViewById(R.id.lvTCs);
        TCList = new ArrayList<TCs>();
        adapter = new TCAdapter(this, TCList);
        btn_TCsearch = (Button) findViewById(R.id.btn_tc_search);
        lvtc.setAdapter(adapter);
        HttpUtils.getTCJSON(GET_TCs_URL, getTCsHandler);
        lvtc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                System.out.println("Teacher_Name = " + TCList.get(i).getTeacher_name());
                Intent intent = new Intent();
                intent.setClass(TC_Search.this, TC_Detail.class);
                intent.putExtra("TC_Id", TCList.get(i).getId());
                intent.putExtra("TC_Teacher_Name", TCList.get(i).getTeacher_name());
                intent.putExtra("TC_These_Course", TCList.get(i).getThese_courses());
                intent.putExtra("TC_Others", TCList.get(i).getOthers());
                intent.putExtra("TC_Answer_Time", TCList.get(i).getAnswer_time());
                startActivity(intent);
            }
        });
        btn_TCsearch.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                TCList.clear();
                Log.e("TC", "点击查询按钮");
                String GET_SEARCH_TCs_URL = GET_TCs_URL + "?teacher_name=" + et_T_N.getText().toString() + "&these_courses=" + et_T_Cs.getText().toString();
                System.out.println("查询URL = " + GET_SEARCH_TCs_URL);
                HttpUtils.getTCJSON(GET_SEARCH_TCs_URL, getTCsHandler);
                //
            }
        });
    }
}
