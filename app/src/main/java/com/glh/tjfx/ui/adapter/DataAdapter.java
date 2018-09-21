package com.glh.tjfx.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.glh.tjfx.R;
import com.glh.tjfx.bean.ChartItem;
import com.glh.tjfx.bean.DataListEntity;

import java.util.List;

/**
 * Created by Yang on 2017/10/11.
 */

public class DataAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private LayoutInflater mLayoutInfater;
    private List<DataListEntity> mList;

    public DataAdapter(Context context, List<DataListEntity> list) {
        mContext = context;
        mLayoutInfater = LayoutInflater.from(context);
        mList = list;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInfater.inflate(R.layout.item_data, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        String timeStr = mList.get(position).getCreatetime();
        viewHolder.txtTime.setText(timeStr.split(" ")[0] + "\n" + timeStr.split(" ")[1]);
        viewHolder.txtWell.setText(mList.get(position).getWellhead());
        viewHolder.txtStation.setText(mList.get(position).getCoalunit());
        viewHolder.txtCount.setText(mList.get(position).getWeight() + "吨");
        viewHolder.txtMoney.setText(mList.get(position).getCoallevel());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTime;
        TextView txtWell;
        TextView txtStation;
        TextView txtCount;
        TextView txtMoney;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTime = itemView.findViewById(R.id.txt_time);
            txtWell = itemView.findViewById(R.id.txt_well);
            txtStation = itemView.findViewById(R.id.txt_station);
            txtCount = itemView.findViewById(R.id.txt_count);
            txtMoney = itemView.findViewById(R.id.txt_money);
        }

    }
}
