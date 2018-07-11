package com.summer.chat.chat.chat;

//by summer on 2018-07-09.

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.summer.chat.R;
import com.summer.chat.chat.bean.CustomMessage;
import com.summer.chat.chat.bean.Message;
import com.summer.chat.chat.bean.MessageFactory;
import com.summer.chat.chat.bean.TextMessage;
import com.summer.chat.chat.chat.interf.ChatView;
import com.summer.chat.databinding.ActChatChatBinding;
import com.summer.chat.databinding.FragChatChatBinding;
import com.summer.chat.databinding.FragMainConversationBinding;
import com.summer.chat.view.ChatInput;
import com.summer.chat.view.TemplateTitle;
import com.tencent.imcore.FriendProfile;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageStatus;
import com.tencent.imsdk.ext.message.TIMMessageDraft;
import com.tencent.imsdk.ext.message.TIMMessageLocator;

import java.util.List;

public class ChatUIOpe extends BaseUIOpe<ActChatChatBinding>{

    @Override
    public void initUI() {
        super.initUI();
    }


    public void initList(final List<Message> messageList){
        getBind().list.setAdapter(new ChatAdapter(getActivity(), R.layout.item_message, messageList));
        getBind().list.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        getBind().list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        getBind().inputPanel.setInputMode(ChatInput.InputMode.NONE);
                        break;
                }
                return false;
            }
        });
        getActivity().registerForContextMenu(getBind().list);
    }

    public void initList(final List<Message> messageList, final OnFinishListener onFinishListener){
        getBind().list.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int firstItem;
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && firstItem == 0) {
                    //如果拉到顶端读取更多消息
                    onFinishListener.onFinish(getBind().list);

                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                firstItem = firstVisibleItem;
            }
        });
    }

    public void refreshList(int newMsgNum){
        ((ChatAdapter)getBind().list.getAdapter()).notifyDataSetChanged();
        getBind().list.setSelection(newMsgNum);
    }

    public void initTitle(TIMConversationType type){
    }


    public void showMessage(TIMMessage message) {
        if (message == null) {
            ((ChatAdapter)getBind().list.getAdapter()).notifyDataSetChanged();
        } else {
            Message mMessage = MessageFactory.getMessage(message);
            if (mMessage != null) {
                if (mMessage instanceof CustomMessage){
                    CustomMessage.Type messageType = ((CustomMessage) mMessage).getType();
                    switch (messageType){
                        case TYPING:
                            break;
                        default:
                            break;
                    }
                }else{
                }

            }
        }

    }

//    public boolean getText(String[] text){
//        if(getBind().etInput.getText().length()==0){
//            return  false;
//        }
//        text[0] = getBind().etInput.getText().toString();
//        return true;
//    }

}
