package com.summer.chat.main.contact;

//by summer on 2018-07-09.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.summer.chat.R;
import com.summer.chat.account.regist.RegistB;
import com.summer.chat.chat.userinfo.UserInfoAct;
import com.summer.chat.search.addnew.AddNewAct;

import java.util.Collection;

import butterknife.OnClick;
import butterknife.Optional;

public class ContactFrag extends BaseUIFrag<ContactUIOpe,ContactDAOpe,ContactValue> implements ViewListener{

    @Override
    public void initNow() {
        super.initNow();
        getP().getD().refresh(getP().getV().getGroups(), getP().getV().getFriends(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getV().getRegistBS().addAll((Collection<? extends RegistB>) o);
                getP().getU().initContact(getP().getV().getRegistBS(),ContactFrag.this);
            }
        });
    }

    @Optional
    @OnClick({R.id.iv_add,R.id.add_friend})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_add:
                getP().getU().showAddDialog();
                break;
            case R.id.add_friend:
                AddNewAct.goTo(getContext());
                break;
        }
    }

    @Override
    public void onInterupt(int i, View view) {
        switch (i){
            case ViewListener.TYPE_ONCLICK:
                RegistB registB = (RegistB) view.getTag(R.id.data);
                UserInfoAct.goTo(getBaseAct(),registB);
                break;
        }
    }
}
