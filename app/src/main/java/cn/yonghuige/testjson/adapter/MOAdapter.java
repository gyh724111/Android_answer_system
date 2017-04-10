package cn.yonghuige.testjson.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.yonghuige.testjson.R;
import cn.yonghuige.testjson.avtivitys.MyOrdersActivity;
import cn.yonghuige.testjson.model.MOs;
import cn.yonghuige.testjson.model.TCs;
import cn.yonghuige.testjson.utils.HttpUtils;
import cn.yonghuige.testjson.utils.Server_IPAddress;

/**
 * Created by gyh on 2017/3/4.
 */

public class MOAdapter extends BaseAdapter {


    private Context context;
    private List<MOs> mosList;

    public MOAdapter(Context context, List<MOs> mosList) {
        this.context = context;
        this.mosList = mosList;
    }

    @Override
    public int getCount() {
        return mosList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.mo_item, null);
        }
        TextView tv_mo_These_course = (TextView) view.findViewById(R.id.tv_mo_These_courses);
        TextView tv_mo_Teacher_name = (TextView) view.findViewById(R.id.tv_mo_Teacher_name);
        TextView tv_mo_Others = (TextView)view.findViewById(R.id.tv_mo_others);
        Button btn_cancel_myorder = (Button) view.findViewById(R.id.btn_cancel_order);

        final MOs mos = mosList.get(position);
        tv_mo_These_course.setText(mos.getThese_course());
        tv_mo_Teacher_name.setText(mos.getTeacher_name());
        tv_mo_Others.setText(mos.getOthers());
        btn_cancel_myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Log.e("MOAdapter",String.valueOf(mos.getOrder_answer_id()));
                String CANCEL_MyOrder_URL = Server_IPAddress.IP + "/cancel_order_answer.php?order_answer_id=" +
                        String.valueOf(mos.getOrder_answer_id());
                Log.e("MOAdapter",CANCEL_MyOrder_URL);
                HttpUtils.cancel_order(CANCEL_MyOrder_URL,MyOrdersActivity.refreshMOsHandler);
            }
        });
        Log.e("MOAdapter","These_Course="+mos.getThese_course()+"Teacher_Name="+mos.getTeacher_name()+"others="+mos.getOthers());
        return view;
    }
}
