package com.glh.tjfx.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.glh.tjfx.R;
import com.glh.tjfx.app.Constants;
import com.glh.tjfx.base.BaseFragment;
import com.glh.tjfx.bean.line.CurrentLineEntity;
import com.glh.tjfx.bean.pie.CurrentPieEntity;
import com.glh.tjfx.presenter.impl.StationPresenterImpl;
import com.glh.tjfx.ui.activity.LineActivity;
import com.glh.tjfx.ui.activity.PieActivity;
import com.glh.tjfx.ui.activity.SameTimeActivity;
import com.glh.tjfx.ui.adapter.StationAdapter;
import com.glh.tjfx.ui.adapter.WellAdapter;
import com.glh.tjfx.ui.interfaces.IMainView;
import com.glh.tjfx.ui.interfaces.IStationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;


/**
 * 分站点统计
 */
public class StationFragment extends BaseFragment<IStationView, StationPresenterImpl> implements IStationView {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList mList = new ArrayList();
    private StationAdapter adapter;

    // 主页面回调接口
    private IMainView mainView;

    private CurrentLineEntity sameTiemEntity;
    private CurrentLineEntity lineEntity;
    private CurrentPieEntity pieEntity;

    private String spinnerTimeStatus;
    private String spinnerWellStatus;
    private String spinnerStationStatus;
    private String spinnerGradeOfCoalStatus;

    public void setCallBackView(IMainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, Constants.TYPE_NUMBER_WELL);
        setArguments(args);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_well;
    }

    @Override
    public void initView(View view) {
        recyclerView = view.findViewById(R.id.rec);
        mList = new ArrayList();
        mList.add(null);
        mList.add(null);
        mList.add(null);
        mList.add(null);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new StationAdapter(getActivity(), mList);
        adapter.setOnWellListner(new StationAdapter.StationListner() {
            @Override
            public void goActivity(int type) {
                if (Constants.FORM_SAME_TIME == type) {
                    Intent intent = new Intent(getActivity(), SameTimeActivity.class);
                    intent.putExtra("data", (Serializable) sameTiemEntity);
                    startActivity(intent);
                } else if (Constants.FORM_LINE == type) {
                    Intent intent = new Intent(getActivity(), LineActivity.class);
                    intent.putExtra("data", (Serializable) lineEntity);
                    startActivity(intent);
                } else if (Constants.FORM_PIE == type) {
                    Intent intent = new Intent(getActivity(), PieActivity.class);
                    intent.putExtra("data", (Serializable) pieEntity);
                    startActivity(intent);
                }
            }
        });
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        mPresenter.queryContent(spinnerTimeStatus, spinnerStationStatus, spinnerWellStatus, spinnerGradeOfCoalStatus);
    }

    @Override
    protected StationPresenterImpl getPresenter() {
        return new StationPresenterImpl();
    }

    @Override
    public void setProgressCancelListener(DialogInterface.OnCancelListener onCancelListener) {

    }

    @Override
    public void setSameContent(CurrentLineEntity entity, String type) {
        mList.set(0, entity.getStatisticsInfo());
        mList.set(1, entity);
        adapter.notifyDataSetChanged();
        sameTiemEntity = entity;
    }

    @Override
    public void setLineContent(CurrentLineEntity entity, String type) {
        mList.set(2, entity);
        adapter.notifyDataSetChanged();
        lineEntity = entity;
    }

    @Override
    public void setPieContent(CurrentPieEntity entity, String type) {
        mList.set(3, entity);
        adapter.notifyDataSetChanged();
        pieEntity = entity;
    }

    public void settingQueryConditions(Map<String, String> map) {
        boolean b1 = TextUtils.equals(spinnerTimeStatus, map.get("spinnerTimeStatus"));
        boolean b2 = TextUtils.equals(spinnerWellStatus, map.get("spinnerWellStatus"));
        boolean b3 = TextUtils.equals(spinnerStationStatus, map.get("spinnerStationStatus"));
        boolean b4 = TextUtils.equals(spinnerGradeOfCoalStatus, map.get("spinnerGradeOfCoalStatus"));

        if (!b1) {
            spinnerTimeStatus = map.get("spinnerTimeStatus");
        }

        if (!b2) {
            spinnerWellStatus = map.get("spinnerWellStatus");
        }

        if (!b3) {
            spinnerStationStatus = map.get("spinnerStationStatus");
        }

        if (!b4) {
            spinnerGradeOfCoalStatus = map.get("spinnerGradeOfCoalStatus");
        }

        if (!b1 | !b2 | !b3 | !b4) {
            if (mPresenter != null) {
                mPresenter.queryContent(spinnerTimeStatus, spinnerStationStatus, spinnerWellStatus, spinnerGradeOfCoalStatus);
            }
        }
    }
}
