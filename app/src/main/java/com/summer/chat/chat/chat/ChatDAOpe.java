package com.summer.chat.chat.chat;

//by summer on 2018-07-09.

import android.support.annotation.Nullable;
import android.util.Log;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.summer.chat.chat.bean.CustomMessage;
import com.summer.chat.chat.bean.Message;
import com.summer.chat.chat.bean.MessageFactory;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageStatus;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.message.TIMConversationExt;

import java.util.List;

public class ChatDAOpe extends BaseDAOpe{


    public void getMessage(final boolean[] isGetingMessage, TIMConversation conversation, @Nullable TIMMessage message, final OnFinishListener onFinishListener){
        if (!isGetingMessage[0]){
            isGetingMessage[0] = true;
            TIMConversationExt timConversationExt = new TIMConversationExt(conversation);
            timConversationExt.getMessage(20, message, new TIMValueCallBack<List<TIMMessage>>() {
                @Override
                public void onError(int i, String s) {
                    isGetingMessage[0] = false;

                }

                @Override
                public void onSuccess(List<TIMMessage> timMessages) {
                    isGetingMessage[0] = false;
                    onFinishListener.onFinish(timMessages);
                }
            });
        }

    }

    public void sendMessage(TIMConversation conversation,final TIMMessage message) {
        conversation.sendMessage(message, new TIMValueCallBack<TIMMessage>() {
            @Override
            public void onError(int code, String desc) {//发送消息失败
                //错误码code和错误描述desc，可用于定位请求失败原因
                //错误码code含义请参见错误码表
                //view.onSendMessageFail(code, desc, message);
            }

            @Override
            public void onSuccess(TIMMessage msg) {
                //发送消息成功,消息状态已在sdk中修改，此时只需更新界面
                //MessageEvent.getInstance().onNewMessage(null);

            }
        });
        //message对象为发送中状态
        //MessageEvent.getInstance().onNewMessage(message);
    }

    public int dealMessage(List<Message> messageList, List<TIMMessage> messages){
        int newMsgNum = 0;
        for (int i = 0; i < messages.size(); ++i){
            Message mMessage = MessageFactory.getMessage(messages.get(i));
            if (mMessage == null || messages.get(i).status() == TIMMessageStatus.HasDeleted) continue;
            if (mMessage instanceof CustomMessage && (((CustomMessage) mMessage).getType() == CustomMessage.Type.TYPING ||
                    ((CustomMessage) mMessage).getType() == CustomMessage.Type.INVALID)) continue;
            ++newMsgNum;
            if (i != messages.size() - 1){
                mMessage.setHasTime(messages.get(i+1));
                messageList.add(0, mMessage);
            }else{
                mMessage.setHasTime(null);
                messageList.add(0, mMessage);
            }
        }
        return newMsgNum;
    }


}
