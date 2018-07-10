package com.summer.chat.main.contact;

//by summer on 2018-07-09.

import com.android.lib.base.ope.BaseValue;
import com.summer.chat.account.regist.RegistB;
import com.tencent.imcore.FriendProfile;
import com.tencent.imsdk.TIMUserProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactValue extends BaseValue{

    private List<String> groups = new ArrayList<>();;
    private Map<String, List<TIMUserProfile>> friends = new HashMap<>();
    private List<RegistB> registBS = new ArrayList<>();

    public List<String> getGroups() {
        return groups;
    }

    public Map<String, List<TIMUserProfile>> getFriends() {
        return friends;
    }

    public List<RegistB> getRegistBS() {
        String[] strings = new String[friends.keySet().toArray().length];
        friends.keySet().toArray(strings);
        registBS.clear();
        for(int i=0;i<strings.length;i++){
           for(int j=0;j<friends.get(strings[i]).size();j++){
               RegistB registB = new RegistB();
               registB.setId(friends.get(strings[i]).get(j).getIdentifier());
               registBS.add(registB);
           }
        }
        return registBS;
    }
}
