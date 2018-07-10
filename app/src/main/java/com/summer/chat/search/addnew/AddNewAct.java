package com.summer.chat.search.addnew;

//by summer on 2018-07-09.

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.summer.chat.R;
import com.summer.chat.account.regist.RegistB;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.sns.TIMUserSearchSucc;

public class AddNewAct extends BaseUIActivity<AddNewUIOpe,AddNewDAOpe,AddNewValue> implements OnFinishListener,ViewListener{

    public static void goTo(Context context){
        context.startActivity(new Intent(context,AddNewAct.class));
    }

    @Override
    protected void initNow() {
        super.initNow();
    }


    @Override
    public void onFinish(Object o) {
        getP().getD().searchFriendByName(o.toString(), getP().getV().getUsers(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().loadListData(getP().getV().getUsers(),AddNewAct.this);
            }
        });
    }

    @Override
    public void onInterupt(int i, View view) {
        switch (i){
            case ViewListener.TYPE_ONCLICK:
                RegistB registB = (RegistB) view.getTag(R.id.data);
                getP().getD().delFriend(registB.getId());
                //getP().getD().addFriend(registB.getId(),"","","");
                break;
        }
    }
}
