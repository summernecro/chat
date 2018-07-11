package com.summer.chat.main.conversation;

//by summer on 2018-07-09.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.listener.ViewListener;
import com.summer.chat.R;
import com.summer.chat.account.regist.RegistB;
import com.summer.chat.chat.chat.ChatAct;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.ext.message.TIMManagerExt;

import java.util.List;

public class ConversationFrag extends BaseUIFrag<ConversationUIOpe,ConversationDAOpe,ConversationValue> implements ViewListener{

    @Override
    public void initNow() {
        super.initNow();
        getP().getU().initList(getP().getD().getConversations(),this);
    }

    @Override
    public void onInterupt(int i, View view) {
        switch (i){
            case ViewListener.TYPE_ONCLICK:
                ConversationBean conversationBean = (ConversationBean) view.getTag(R.id.data);
                RegistB registB =new RegistB();
                registB.setId(conversationBean.getName());
                ChatAct.goTo(getBaseAct(),conversationBean.getName(), TIMConversationType.C2C);
                break;
        }
    }
}
