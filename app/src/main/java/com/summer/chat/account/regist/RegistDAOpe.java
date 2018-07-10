package com.summer.chat.account.regist;

//by summer on 2018-07-09.

import com.android.lib.base.ope.BaseDAOpe;
import com.summer.chat.chat.chatinit.ChatInit;

import tencent.tls.platform.TLSStrAccRegListener;

public class RegistDAOpe extends BaseDAOpe{

    public void regist(RegistB registB, TLSStrAccRegListener listener){
        ChatInit.getInstance().regist(registB.getName(),registB.getPwd(),listener);
    }
}
