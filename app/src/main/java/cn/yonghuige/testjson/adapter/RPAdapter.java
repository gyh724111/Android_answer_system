package cn.yonghuige.testjson.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.yonghuige.testjson.R;
import cn.yonghuige.testjson.model.RP;

/**
 * Created by gyh on 2017/3/20.
 */

public class RPAdapter extends BaseAdapter {
    private Context context;
    private List<RP> rpList;

    public RPAdapter(Context context, List<RP> rpList) {
        this.context = context;
        this.rpList = rpList;
    }

    @Override
    public int getCount() {
        return rpList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return rpList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.rp_item, null);
        }
        TextView tv_rp_id = (TextView) view.findViewById(R.id.tv_rp_id);
        TextView tv_room_division = (TextView) view.findViewById(R.id.tv_room_division);
        TextView tv_room_num = (TextView) view.findViewById(R.id.tv_room_num);
        TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);
        TextView tvOthers = (TextView)view.findViewById(R.id.tv_rp_others);

        RP rp = rpList.get(position);
        tv_rp_id.setText(rp.getId());
        tv_room_division.setText(rp.getRoom_division());
        tv_room_num.setText(rp.getRoom_num());
        tv_phone.setText(rp.getPhone());
        tvOthers.setText(rp.getOthers());
        return view;
    }
}
