package com.summer.chat.main.contact;

//by summer on 2018-07-09.

import android.util.Log;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.tencent.imcore.FriendProfile;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.sns.TIMFriendGroup;
import com.tencent.imsdk.ext.sns.TIMFriendshipManagerExt;
import com.tencent.imsdk.ext.sns.TIMFriendshipProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContactDAOpe extends BaseDAOpe{


    public void refresh(List<String> groups, final Map<String, List<TIMUserProfile>> friends, final OnFinishListener onFinishListener){
        groups.clear();
        friends.clear();
        TIMFriendshipManagerExt.getInstance().getFriendList(new TIMValueCallBack<List<TIMUserProfile>>(){
            @Override
            public void onError(int code, String desc){
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.e("1", "getFriendList failed: " + code + " desc");
            }

            @Override
            public void onSuccess(List<TIMUserProfile> result){
                friends.put("",result);
                onFinishListener.onFinish(result);
            }
        });
    }

}
