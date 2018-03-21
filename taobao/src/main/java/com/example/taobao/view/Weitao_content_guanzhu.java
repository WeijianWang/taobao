package com.example.taobao.view;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentTabHost;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.taobao.R;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 王伟健 on 2018-03-17.
 */

public class Weitao_content_guanzhu extends Fragment {

    private Map<String,Map<String,Object>> topTabMap;
    private static String LAST_SELECT = "quanbu";
    private FragmentTabHost myTabHost;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_gunzhu, container, false);
        this.view = view;
        topTabMap = new HashMap<>();
        initTabHost();
        myTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Map attr = topTabMap.get(LAST_SELECT);
                View view = (View) attr.get("view");
                //修改上一次点击的布局
                TextView tv = view.findViewById(R.id.tv);
                tv.setTextColor(Color.rgb(132,132,132));
                LAST_SELECT = tabId;
                //改此次点击的图标
                view = (View)(topTabMap.get(tabId).get("view"));
                tv = view.findViewById(R.id.tv);
                tv.setTextColor(Color.rgb(255,80,0));
            }
        });

        return view;
    }

    private void initTabHost(){
        myTabHost = view.findViewById(android.R.id.tabhost);
        myTabHost.setup(view.getContext(),getChildFragmentManager(),android.R.id.tabcontent);

        addTabSpec("quanbu","全部",Fragement_weitao_guanzhu_quanbu.class);
        addTabSpec("shangxin","上新",Fragement_weitao_guanzhu_shangxin.class);
        addTabSpec("shipinzhibo","视频直播",Fragement_weitao_guanzhu_shipinzhibo.class);
        addTabSpec("tebieguanzhu","特别关注",Fragement_weitao_guanzhu_tebieguanzhu.class);
        addTabSpec("daren","达人",Fragement_weitao_guanzhu_daren.class);

        myTabHost.setCurrentTab(0);
        View v = (View)topTabMap.get("quanbu").get("view");
        TextView tv = v.findViewById(R.id.tv);
        tv.setTextColor(Color.rgb(255,80,0));
    }

    /**
     * @param str 标签名字
     * @param id tabid
     * */
    private void addTabSpec(String id,String str,Class<?> fragment){
        Map<String,Object> attr = new HashMap<>();
        View tab  = getTabView(str);
        FragmentTabHost.TabSpec tabSpec = myTabHost.newTabSpec(id)
                .setIndicator(tab);
        myTabHost.addTab(tabSpec,fragment,null);
        attr.put("view",tab);
        attr.put("str",str);
        topTabMap.put(id,attr);
    }

    /**
     * 创建tabview
     * */
    private View getTabView(String str) {
        View view = getLayoutInflater().inflate(R.layout.layout_weitao_socend_tab, null);
        TextView tv = view.findViewById(R.id.tv);
        tv.setText(str);
        return view;
    }
}
