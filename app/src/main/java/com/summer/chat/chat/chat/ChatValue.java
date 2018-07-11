package com.summer.chat.chat.chat;

//by summer on 2018-07-09.

import com.android.lib.base.ope.BaseValue;
import com.summer.chat.account.regist.RegistB;
import com.summer.chat.chat.bean.Message;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatValue extends BaseValue{

    private String identify;

    private TIMConversationType type;

    private String[] text  = new String[1];

    private TIMConversation timConversation;

    private List<Message> messageList = new ArrayList<>();

    private boolean[] isGetingMessage = new boolean[1];
}
