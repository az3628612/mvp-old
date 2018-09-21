package com.glh.tjfx.ui.fragment;

import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.andview.refreshview.XRefreshView;
import com.glh.tjfx.R;
import com.glh.tjfx.base.BaseFragment;
import com.glh.tjfx.bean.DataEntity;
import com.glh.tjfx.bean.DataListEntity;
import com.glh.tjfx.presenter.impl.DataPresenterImpl;
import com.glh.tjfx.ui.adapter.DataAdapter;
import com.glh.tjfx.ui.interfaces.IDataView;

import java.util.ArrayList;
import java.util.Map;

/**
 * 详细数据
 */
public class DataFragment extends BaseFragment<IDataView, DataPresenterImpl> implements IDataView {

    XRefreshView xrefreshview;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<DataListEntity> mList = new ArrayList();
    private DataAdapter adapter;

    private String spinnerTimeStatus;
    private String spinnerWellStatus;
    private String spinnerStationStatus;
    private String spinnerGradeOfCoalStatus;

    private int start = 0;
    private int length = 20;
    private int page = 1;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_data;
    }

    @Override
    public void initView(View view) {
        xrefreshview = view.findViewById(R.id.xrefreshview);
        recyclerView = view.findViewById(R.id.rec);
        mList = new ArrayList();
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new DataAdapter(getActivity(), mList);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        start = 0;
        mPresenter.queryContent(start, length, spinnerTimeStatus, spinnerStationStatus, spinnerWellStatus, spinnerGradeOfCoalStatus);
        setXrefreshview();
    }

    @Override
    protected DataPresenterImpl getPresenter() {
        return new DataPresenterImpl();
    }

    @Override
    public void setProgressCancelListener(DialogInterface.OnCancelListener onCancelListener) {

    }

    @Override
    public void setContent(DataEntity entity) {
        xrefreshview.stopRefresh();
        xrefreshview.stopLoadMore();
        mList.addAll(entity.getData());
        adapter.notifyDataSetChanged();
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
                start = 0;
                mList.clear();
                mPresenter.queryContent(start, length, spinnerTimeStatus, spinnerStationStatus, spinnerWellStatus, spinnerGradeOfCoalStatus);
            }
        }
    }

    private void setXrefreshview() {
        xrefreshview.setAutoLoadMore(true);
        xrefreshview.setPullLoadEnable(true);
        xrefreshview.setPinnedTime(1000);
        xrefreshview.setMoveForHorizontal(true);
        xrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                mList.clear();
                start = 0;
                mPresenter.queryContent(start, length, spinnerTimeStatus, spinnerStationStatus, spinnerWellStatus, spinnerGradeOfCoalStatus);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                start = mList.size();
                mPresenter.queryContent(start, length, spinnerTimeStatus, spinnerStationStatus, spinnerWellStatus, spinnerGradeOfCoalStatus);
            }
        });
    }
}
