package com.summer.chat.chat.chat;

//by summer on 2018-07-09.

import com.android.lib.base.ope.BaseUIOpe;
import com.summer.chat.databinding.ActChatChatBinding;
import com.summer.chat.databinding.FragChatChatBinding;
import com.summer.chat.databinding.FragMainConversationBinding;

public class ChatUIOpe extends BaseUIOpe<ActChatChatBinding>{


    public boolean getText(String[] text){
        if(getBind().etInput.getText().length()==0){
            return  false;
        }
        text[0] = getBind().etInput.getText().toString();
        return true;
    }

}
