package cn.yonghuige.testjson.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.yonghuige.testjson.R;
import cn.yonghuige.testjson.model.MOed;
import cn.yonghuige.testjson.model.TCs;

/**
 * Created by gyh on 2017/3/4.
 */

public class MOedAdapter extends BaseAdapter {

    private Context context;
    private List<MOed> moedList;

    public MOedAdapter(Context context, List<MOed> moedList) {
        this.context = context;
        this.moedList = moedList;
    }

    @Override
    public int getCount() {
        return moedList.size();
    }

    @Override
    public Object getItem(int position) {
        return moedList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.moed_item, null);
        }
        TextView tv_moed_these_courses = (TextView) view.findViewById(R.id.tv_myordered_These_courses);
        TextView tv_moed_Answer_Time = (TextView) view.findViewById(R.id.tv_myordered_Answer_Time);
        TextView tv_moed_Answer_Position = (TextView) view.findViewById(R.id.tv_myordered_Answer_Position);
        TextView tv_moed_Others = (TextView) view.findViewById(R.id.tv_myordered_Others);
        TextView tv_moed_count = (TextView) view.findViewById(R.id.tv_countallordered);

        MOed moed = moedList.get(position);
        tv_moed_these_courses.setText(moed.getThese_course());
        tv_moed_Answer_Time.setText(moed.getAnswer_time());
        tv_moed_Answer_Position.setText(moed.getAnswer_position());
        tv_moed_Others.setText(moed.getOthers());
        tv_moed_count.setText("已预约\n" + String.valueOf(moed.getCount()) + "人");
        return view;
    }
}
