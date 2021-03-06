package com.summer.chat.account.regist;

//by summer on 2018-07-09.

import com.android.lib.bean.BaseBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistB extends BaseBean{

    private String name;

    private String pwd;

    private String id;

    private String userSig;

    private String type;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }

    public String getUserSig() {
        return userSig;
    }

    public String getType() {
        return type;
    }
}
