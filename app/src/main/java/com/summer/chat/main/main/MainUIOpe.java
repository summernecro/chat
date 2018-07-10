package com.summer.chat.main.main;

//by summer on 2018-07-09.

import android.view.View;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.fragment.two.FragManager2;
import com.android.lib.view.bottommenu.BottomMenuBean;
import com.summer.chat.R;
import com.summer.chat.databinding.ActMainBinding;

import java.util.ArrayList;

public class MainUIOpe extends BaseUIOpe<ActMainBinding>{

    @Override
    public void initUI() {
        super.initUI();
    }

    public void initFrag(ArrayList<BottomMenuBean> bottomMenuBeans,BaseUIActivity activity, ArrayList<BaseUIFrag> fragments, int[] 模块ID, String[] 模块){
        bottomMenuBeans.add(new BottomMenuBean("记录", R.drawable.drawable_record_main_bottom_video,null,getBind().containChat,getActivity().getResources().getColorStateList(R.color.color_white_black)));
        bottomMenuBeans.add(new BottomMenuBean("通讯", R.drawable.drawable_record_main_bottom_image,null,getBind().containContact,getActivity().getResources().getColorStateList(R.color.color_white_black)));
        bottomMenuBeans.add(new BottomMenuBean("社区", R.drawable.drawable_record_main_bottom_text,null,getBind().containCommunity,getActivity().getResources().getColorStateList(R.color.color_white_black)));
        bottomMenuBeans.add(new BottomMenuBean("设置", R.drawable.drawable_record_main_bottom_setting,null,getBind().containSetting,getActivity().getResources().getColorStateList(R.color.color_white_black)));
        getBind().bottommenu.initItems(bottomMenuBeans);
        if(getActivity() instanceof OnAppItemSelectListener){
            getBind().bottommenu.setOnAppItemClickListener((OnAppItemSelectListener)getActivity());
            getBind().bottommenu.setIndex(0);
        }
        for(int i=0;i<fragments.size();i++){
            FragManager2.getInstance().setAnim(false).start(activity,模块[i],模块ID[i],fragments.get(i));
        }
    }

    public void showView(ArrayList<BottomMenuBean> bottomMenuBeans,int pos){
        for(int i=0;i<bottomMenuBeans.size();i++){
            if(i==pos){
                bottomMenuBeans.get(i).getContainerView().setVisibility(View.VISIBLE);
            }else{
                bottomMenuBeans.get(i).getContainerView().setVisibility(View.GONE);
            }
        }
    }
}
