package com.summer.chat.main.conversation;

//by summer on 2018-07-09.

import android.util.Log;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.LogUtil;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.message.TIMConversationExt;
import com.tencent.imsdk.ext.message.TIMManagerExt;

import java.util.ArrayList;
import java.util.List;

public class ConversationDAOpe extends BaseDAOpe{

    public ArrayList<ConversationBean> getConversations(){
        final List<TIMConversation> timConversations = TIMManagerExt.getInstance().getConversationList();
        ArrayList<ConversationBean> conversationBeans = new ArrayList<>();
        for(int i=0;i<timConversations.size();i++){
            ConversationBean  conversationBean = new ConversationBean();
            conversationBean.setName(timConversations.get(i).getPeer());
            conversationBean.setType(timConversations.get(i).getType());
            TIMConversationExt ext = new TIMConversationExt(timConversations.get(i));
            ext.getMessage(1, null, new TIMValueCallBack<List<TIMMessage>>() {
                @Override
                public void onError(int i, String s) {
                    LogUtil.E(s);
                }

                @Override
                public void onSuccess(List<TIMMessage> timMessages) {
                    LogUtil.E(timMessages);
                }
            });
            conversationBean.setUnread(ext.getUnreadMessageNum());
            conversationBeans.add(conversationBean);
        }
        return conversationBeans;
    }
}
