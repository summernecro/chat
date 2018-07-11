package com.summer.chat.main.conversation;

//by summer on 2018-07-11.

import com.android.lib.bean.BaseBean;
import com.tencent.imsdk.TIMConversationType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversationBean extends BaseBean{

    private String name;

    private String avatar;

    private String lastText;

    private String time;

    private long unread;

    private TIMConversationType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLastText() {
        return lastText;
    }

    public void setLastText(String lastText) {
        this.lastText = lastText;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public TIMConversationType getType() {
        return type;
    }

    public void setType(TIMConversationType type) {
        this.type = type;
    }

    public long getUnread() {
        return unread;
    }

    public void setUnread(long unread) {
        this.unread = unread;
    }
}
