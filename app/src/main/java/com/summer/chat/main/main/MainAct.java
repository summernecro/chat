package com.summer.chat.main.main;

//by summer on 2018-07-09.

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.summer.chat.R;
import com.summer.chat.chat.chatinit.ChatInit;

import butterknife.OnClick;
import butterknife.Optional;

public class MainAct extends BaseUIActivity<MainUIOpe,MainDAOpe,MainValue> implements OnAppItemSelectListener,View.OnClickListener{

    public static void goTo(Context context){
        context.startActivity(new Intent(context,MainAct.class));
    }

    @Override
    protected void initNow() {
        super.initNow();
        getP().getU().initFrag(getP().getV().getBottomMenuBeans(),this,getP().getV().getFragments(),MainValue.模块ID,MainValue.模块);
        onAppItemSelect(null,null,0);
        ChatInit.getInstance().addMessageListener(this);
    }

    @Override
    public void onAppItemSelect(ViewGroup viewGroup, View view, int i) {
        getP().getU().showView(getP().getV().getBottomMenuBeans(),i);
        setMoudle(MainValue.模块[i]);
        getP().getV().setPos(i);
    }

    @Optional
    @OnClick({R.id.iv_add})
    public void onClick(View v) {
        getP().getV().getCurrent().onClick(v);
    }
}
