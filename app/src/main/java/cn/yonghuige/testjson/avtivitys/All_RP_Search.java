package cn.yonghuige.testjson.avtivitys;


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
import cn.yonghuige.testjson.adapter.RPAdapter;
import cn.yonghuige.testjson.model.RP;
import cn.yonghuige.testjson.utils.HttpUtils;
import cn.yonghuige.testjson.utils.Server_IPAddress;

public class All_RP_Search extends AppCompatActivity {
    private Button btn_allRPsearch;
    private EditText et_R_N;
    private EditText et_R_D;
    private EditText et_R_P;
    private ListView lvrp;
    private RPAdapter adapter;
    private List<RP> RPList;
    public static final String GET_allRP_URL = Server_IPAddress.IP + "/getallRP.php";

    private Handler getRPHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonData = (String) msg.obj;
            System.out.println(jsonData);
            try {
                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String id = object.getString("allRP_id");
                    String room_num = object.getString("room_num");
                    String room_division = object.getString("room_division");
                    String phone = object.getString("phone");
                    String others = object.getString("others");
                    RPList.add(new RP(id, room_num, room_division, phone, others));
                    System.out.println("id = " + id);
                    System.out.println("room_num = " + room_num);
                    System.out.println("room_division = " + room_division);
                    System.out.println("phone = " + phone);
                    System.out.println("others = " + others);
                }
                adapter.notifyDataSetChanged();
                Log.e("ALL_RP_SEARCH","adapter.notifyDataSetChanged();");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__rp__search);

        et_R_N = (EditText) findViewById(R.id.et_R_N);
        et_R_D = (EditText) findViewById(R.id.et_R_D);
        et_R_P = (EditText) findViewById(R.id.et_R_P);
        lvrp = (ListView) findViewById(R.id.lvallRPs);
        RPList = new ArrayList<RP>();
        adapter = new RPAdapter(this, RPList);
        btn_allRPsearch = (Button) findViewById(R.id.btn_allrp_search);
        lvrp.setAdapter(adapter);
        HttpUtils.getallRPJSON(GET_allRP_URL, getRPHandler);
//        lvrp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });
        btn_allRPsearch.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //RPList.clear();

                Log.e("RP", "点击查询按钮");
                String GET_SEARCH_allRP_URL = GET_allRP_URL + "?room_num=" + et_R_N.getText().toString() +
                        "&room_division=" + et_R_D.getText().toString() +
                        "&phone=" + et_R_P.getText().toString();
                System.out.println("查询URL = " + GET_SEARCH_allRP_URL);
                HttpUtils.getTCJSON(GET_SEARCH_allRP_URL, getRPHandler);
            }

        });
    }
}
