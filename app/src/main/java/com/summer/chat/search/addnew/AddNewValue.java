package com.summer.chat.search.addnew;

//by summer on 2018-07-09.

import com.android.lib.base.ope.BaseValue;
import com.summer.chat.account.regist.RegistB;
import com.tencent.imsdk.TIMUserProfile;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddNewValue extends BaseValue{

    List<TIMUserProfile> timUserProfiles = new ArrayList<>();

    private ArrayList<RegistB> users = new ArrayList<>();

    public void initUsers(List<TIMUserProfile> timUserProfiles){
        this.timUserProfiles.clear();
        this.timUserProfiles.addAll(timUserProfiles);
        for(int i=0;i<timUserProfiles.size();i++){
            RegistB registB = new RegistB();
            registB.setName(timUserProfiles.get(i).getNickName());
            registB.setId(timUserProfiles.get(i).getIdentifier());
            registB.setUserSig(timUserProfiles.get(i).getSelfSignature());
            users.add(registB);
        }
    }
}
