package com.summer.chat.app;

//by summer on 2018-07-09.

import com.android.lib.aplication.LibAplication;
import com.summer.chat.chat.chatinit.ChatInit;
import com.tencent.imsdk.TIMLogListener;
import com.tencent.imsdk.TIMManager;

public class ChatApp extends LibAplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ChatInit.getInstance().init(getApplicationContext());
        ChatInit.getInstance().setUserConfig();
    }

    @Override
    public void initImagePicker() {

    }
}
