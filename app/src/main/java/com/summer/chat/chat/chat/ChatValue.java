package com.summer.chat.chat.chat;

//by summer on 2018-07-09.

import com.android.lib.base.ope.BaseValue;
import com.summer.chat.account.regist.RegistB;
import com.tencent.imsdk.TIMConversation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatValue extends BaseValue{

    private RegistB registB;

    private String[] text  = new String[1];

    private TIMConversation timConversation;
}
