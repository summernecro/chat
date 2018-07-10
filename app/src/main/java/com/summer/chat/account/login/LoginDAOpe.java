package com.summer.chat.account.login;

//by summer on 2018-07-09.

import com.android.lib.base.ope.BaseDAOpe;
import com.summer.chat.account.regist.RegistB;
import com.summer.chat.chat.chatinit.ChatInit;

import tencent.tls.platform.TLSPwdLoginListener;

public class LoginDAOpe extends BaseDAOpe{

    public void login(RegistB registB,TLSPwdLoginListener listener){
        ChatInit.getInstance().pwLogin(registB.getName(),registB.getPwd(),listener);
    }
}
