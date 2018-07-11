package com.summer.chat.main.conversation;

//by summer on 2018-07-09.

import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.BR;
import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.summer.chat.R;
import com.summer.chat.databinding.FragMainConversationBinding;

import java.util.ArrayList;

public class ConversationUIOpe extends BaseUIOpe<FragMainConversationBinding>{

    public void initList(ArrayList<ConversationBean> conversationBeans, ViewListener listener){
        if(getBind().recycle.getAdapter()==null){
            getBind().recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
            getBind().recycle.setAdapter(new AppsDataBindingAdapter(getActivity(), R.layout.item_conversation, BR.item_conversation,conversationBeans,listener));
        }
    }
}
