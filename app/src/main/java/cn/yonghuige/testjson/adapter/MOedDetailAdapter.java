package cn.yonghuige.testjson.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cn.yonghuige.testjson.R;
import cn.yonghuige.testjson.avtivitys.MyOrdered_Detail;
import cn.yonghuige.testjson.avtivitys.MyOrdersActivity;
import cn.yonghuige.testjson.model.MOed;
import cn.yonghuige.testjson.model.MOedDetail;
import cn.yonghuige.testjson.utils.HttpUtils;
import cn.yonghuige.testjson.utils.Server_IPAddress;

/**
 * Created by gyh on 2017/3/4.
 */

public class MOedDetailAdapter extends BaseAdapter {

    private Context context;
    private List<MOedDetail> moedDetailList;

    public MOedDetailAdapter(Context context, List<MOedDetail> moedDetailList) {
        this.context = context;
        this.moedDetailList = moedDetailList;
    }

    @Override
    public int getCount() {
        return moedDetailList.size();
    }

    @Override
    public Object getItem(int position) {
        return moedDetailList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.moed_detail_item, null);
        }
        TextView tv_moed_detail_stu_name = (TextView) view.findViewById(R.id.tv_ordereddetail_stu_name);
        TextView tv_moed_detail_stu_id = (TextView) view.findViewById(R.id.tv_ordereddetail_stu_id);
        TextView tv_moed_detail_others = (TextView) view.findViewById(R.id.tv_ordereddetail_Others);
        Button btn_finish_myorder = (Button) view.findViewById(R.id.btn_finish_order);

        final MOedDetail moedDetail = moedDetailList.get(position);
        tv_moed_detail_stu_name.setText(moedDetail.getStu_name());
        tv_moed_detail_stu_id.setText(moedDetail.getStu_id());
        tv_moed_detail_others.setText(moedDetail.getOthers());
        btn_finish_myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("MOAdapter",String.valueOf(moedDetail.getMy_Ordered_id()));
                String CANCEL_MyOrder_URL = Server_IPAddress.IP + "/cancel_order_answer.php?order_answer_id=" +
                        String.valueOf(moedDetail.getMy_Ordered_id());
                Log.e("MOAdapter",CANCEL_MyOrder_URL);
                HttpUtils.finish_order(CANCEL_MyOrder_URL, MyOrdered_Detail.refreshMOedDetailHandler);
            }
        });
        return view;
    }
}
