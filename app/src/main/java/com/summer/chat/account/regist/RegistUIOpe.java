package com.summer.chat.account.regist;

//by summer on 2018-07-09.

import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.ToastUtil;
import com.summer.chat.databinding.ActAccountRegistBinding;

public class RegistUIOpe extends BaseUIOpe<ActAccountRegistBinding> {

    public boolean initRegistB(RegistB registB){
        if(getBind().username.getText().toString().length()==0){
            ToastUtil.getInstance().showShort(getActivity(),"");
            return false;
        }
        if(getBind().password.getText().toString().length()==0){
            ToastUtil.getInstance().showShort(getActivity(),"");
            return false;
        }
        registB.setName(getBind().username.getText().toString());
        registB.setPwd(getBind().password.getText().toString());
        return true;
    }
}
