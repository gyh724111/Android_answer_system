package cn.yonghuige.testjson.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.yonghuige.testjson.R;
import cn.yonghuige.testjson.model.TCs;

/**
 * Created by gyh on 2017/3/4.
 */

public class TCAdapter extends BaseAdapter {

    private Context context;
    private List<TCs> tcsList;

    public TCAdapter(Context context, List<TCs> tcsList) {
        this.context = context;
        this.tcsList = tcsList;
    }

    @Override
    public int getCount() {
        return tcsList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return tcsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.tc_item, null);
        }
        TextView tvId = (TextView) view.findViewById(R.id.tvId);
        TextView tvTeacher_name = (TextView) view.findViewById(R.id.tvTeacher_name);
        TextView tvThese_course = (TextView) view.findViewById(R.id.tvThese_courses);
        TextView tvAnswer_time = (TextView) view.findViewById(R.id.tvAnswer_time);
        TextView tvOthers = (TextView)view.findViewById(R.id.tvOthers);

        TCs tcs = tcsList.get(position);
        tvId.setText(tcs.getId());
        tvTeacher_name.setText(tcs.getTeacher_name());
        tvThese_course.setText(tcs.getThese_courses());
        tvAnswer_time.setText(tcs.getAnswer_time());
        tvOthers.setText(tcs.getOthers());
        return view;
    }
}
