package com.glh.tjfx.ui.activity;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.glh.tjfx.R;
import com.glh.tjfx.base.BaseActivity;
import com.glh.tjfx.base.BaseFragment;
import com.glh.tjfx.bean.QueryConditionCoalEntity;
import com.glh.tjfx.bean.QueryConditionSiteEntity;
import com.glh.tjfx.bean.QueryConditionWellEntity;
import com.glh.tjfx.presenter.impl.MainPresenterImpl;
import com.glh.tjfx.ui.adapter.SectionsPagerAdapter;
import com.glh.tjfx.ui.fragment.DataFragment;
import com.glh.tjfx.ui.fragment.StationFragment;
import com.glh.tjfx.ui.fragment.WellFragment;
import com.glh.tjfx.ui.interfaces.IMainView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends BaseActivity<IMainView, MainPresenterImpl> implements View.OnClickListener, IMainView {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private TextView txtSpinnerTime;
    private TextView txtSpinnerWell;
    private TextView txtSpinnerStation;
    private TextView txtSpinnerGradeOfCoal;

    private List<BaseFragment> list;

    private WellFragment wellFragment;
    private StationFragment stationFragment;
    private DataFragment dataFragment;

    public static String[] QUERY_CONDITION_STATUS = {"currentDay", "currentMonth", "currentYear"};
    private List<QueryConditionWellEntity> queryConditionWellEntityList = new ArrayList<>();
    private List<QueryConditionSiteEntity> queryConditionSiteEntityList = new ArrayList<>();
    private List<QueryConditionCoalEntity> queryConditionCoalEntityList = new ArrayList<>();

    private String[] times;
    private String[] wells = new String[]{"井口"};
    private String[] sites = new String[]{"站点"};
    private String[] coals = new String[]{"煤等级"};

    private int spinnerSameTimeStatus;
    private int spinnerWellStatus = -1;
    private int spinnerStationStatus = -1;
    private int spinnerGradeOfCoalStatus = -1;


    @Override
    public void initView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = new ArrayList<>();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), list);
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        // 查询条件选择菜单
        txtSpinnerTime = findViewById(R.id.spinner_1);
        txtSpinnerWell = findViewById(R.id.spinner_2);
        txtSpinnerStation = findViewById(R.id.spinner_3);
        txtSpinnerGradeOfCoal = findViewById(R.id.spinner_4);
        // 查询条件选择菜单监听
        txtSpinnerTime.setOnClickListener(this);
        txtSpinnerWell.setOnClickListener(this);
        txtSpinnerStation.setOnClickListener(this);
        txtSpinnerGradeOfCoal.setOnClickListener(this);

        initData();

    }

    public void initData() {
        times = getResources().getStringArray(R.array.conditions_time);
        // 设置按钮显示内容
        txtSpinnerTime.setText(times[0]);
        txtSpinnerWell.setText(wells[0]);
        txtSpinnerStation.setText(sites[0]);
        txtSpinnerGradeOfCoal.setText(coals[0]);
        // 初始化时间筛选条件
        spinnerSameTimeStatus = 0;

        wellFragment = new WellFragment();
        wellFragment.setCallBackView(this);
        wellFragment.settingQueryConditions(getQueryConditions());
        stationFragment = new StationFragment();
        stationFragment.setCallBackView(this);
        stationFragment.settingQueryConditions(getQueryConditions());
        dataFragment = new DataFragment();
        dataFragment.settingQueryConditions(getQueryConditions());

        list.add(wellFragment);
        list.add(stationFragment);
        list.add(dataFragment);
        mSectionsPagerAdapter.notifyDataSetChanged();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter.getQueryCondition();
    }

    @Override
    protected MainPresenterImpl getPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.spinner_1:
                showSpinnerDialogOne();
                break;
            case R.id.spinner_2:
                showSpinnerDialogTwo();
                break;
            case R.id.spinner_3:
                showSpinnerDialogThree();
                break;
            case R.id.spinner_4:
                showSpinnerDialogFour();
                break;
        }
    }

    private void showSpinnerDialogOne() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("请选择时间")
                .setItems(times, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which != spinnerSameTimeStatus) {
                            txtSpinnerTime.setText(times[which]);
                            spinnerSameTimeStatus = which;
                            setQueryConditions();
                        }
                    }
                })
                .show();
    }

    private void showSpinnerDialogTwo() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("请选择井口")
                .setItems(wells, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which != spinnerWellStatus + 1) {
                            if (which == 0) {
                                txtSpinnerWell.setText("井口");
                                spinnerWellStatus = -1;
                            } else {
                                txtSpinnerWell.setText(wells[which]);
                                spinnerWellStatus = which - 1;
                            }
                            setQueryConditions();
                        }
                    }
                })
                .show();
    }

    private void showSpinnerDialogThree() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("请选择站点")
                .setItems(sites, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which != spinnerStationStatus + 1) {
                            if (which == 0) {
                                txtSpinnerStation.setText("站点");
                                spinnerStationStatus = -1;
                            } else {
                                txtSpinnerStation.setText(sites[which]);
                                spinnerStationStatus = which - 1;
                            }
                            setQueryConditions();
                        }
                    }
                })
                .show();
    }


    private void showSpinnerDialogFour() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("请选择煤质量")
                .setItems(coals, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which != spinnerGradeOfCoalStatus + 1) {
                            if (which == 0) {
                                txtSpinnerGradeOfCoal.setText("煤质量");
                                spinnerGradeOfCoalStatus = -1;
                            } else {
                                txtSpinnerGradeOfCoal.setText(coals[which]);
                                spinnerGradeOfCoalStatus = which - 1;
                            }
                            setQueryConditions();
                        }
                    }
                })
                .show();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private Map<String, String> getQueryConditions() {
        Map<String, String> map = new HashMap<>();
        map.put("spinnerTimeStatus", QUERY_CONDITION_STATUS[spinnerSameTimeStatus]);
        if (spinnerWellStatus != -1 && queryConditionWellEntityList.size() != 0) {
            map.put("spinnerWellStatus", queryConditionWellEntityList.get(spinnerWellStatus).getWellHeadName() + "");
        }
        if (spinnerStationStatus != -1 && queryConditionSiteEntityList.size() != 0) {
            map.put("spinnerStationStatus", queryConditionSiteEntityList.get(spinnerStationStatus).getSiteName() + "");
        }
        if (spinnerGradeOfCoalStatus != -1 && queryConditionCoalEntityList.size() != 0) {
            map.put("spinnerGradeOfCoalStatus", queryConditionCoalEntityList.get(spinnerGradeOfCoalStatus).getCoalName() + "");
        }
        System.out.println("spinnerTimeStatus" + spinnerSameTimeStatus);
        System.out.println("spinnerWellStatus" + spinnerWellStatus);
        System.out.println("spinnerStationStatus" + spinnerStationStatus);
        System.out.println("spinnerGradeOfCoalStatus" + spinnerGradeOfCoalStatus);


        return map;
    }

    public void setQueryConditions() {
        Map<String, String> map = getQueryConditions();
        wellFragment.settingQueryConditions(map);
        stationFragment.settingQueryConditions(map);
        dataFragment.settingQueryConditions(map);
    }

    @Override
    public void setQueryConditionWell(List<QueryConditionWellEntity> list) {
        queryConditionWellEntityList.addAll(list);
        this.wells = new String[list.size() + 1];
        this.wells[0] = "全部";
        for (int i = 0; i < list.size(); i++) {
            this.wells[i + 1] = list.get(i).getWellHeadName();
        }
    }

    @Override
    public void setQueryConditionSite(List<QueryConditionSiteEntity> list) {
        queryConditionSiteEntityList.addAll(list);
        this.sites = new String[list.size() + 1];
        this.sites[0] = "全部";
        for (int i = 0; i < list.size(); i++) {
            this.sites[i + 1] = list.get(i).getSiteName();
        }
    }

    @Override
    public void setQueryConditionCoal(List<QueryConditionCoalEntity> list) {
        queryConditionCoalEntityList.addAll(list);
        this.coals = new String[list.size() + 1];
        this.coals[0] = "全部";
        for (int i = 0; i < list.size(); i++) {
            this.coals[i + 1] = list.get(i).getCoalName();
        }
    }
}
