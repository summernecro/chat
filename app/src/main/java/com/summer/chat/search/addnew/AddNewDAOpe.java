package com.summer.chat.search.addnew;

//by summer on 2018-07-09.

import android.util.Log;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.LogUtil;
import com.summer.chat.account.regist.RegistB;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.sns.TIMAddFriendRequest;
import com.tencent.imsdk.ext.sns.TIMDelFriendType;
import com.tencent.imsdk.ext.sns.TIMFriendResult;
import com.tencent.imsdk.ext.sns.TIMFriendStatus;
import com.tencent.imsdk.ext.sns.TIMFriendshipManagerExt;
import com.tencent.imsdk.ext.sns.TIMUserSearchSucc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddNewDAOpe extends BaseDAOpe{

    public void searchFriendByName(String key, final ArrayList<RegistB> user, final OnFinishListener onFinishListener){
        user.clear();
        TIMFriendshipManagerExt.getInstance().searchUserByNickname(key, 1, 100, new TIMValueCallBack<TIMUserSearchSucc>() {
            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onSuccess(TIMUserSearchSucc timUserSearchSucc) {
              for(int i=0;i<timUserSearchSucc.getInfoList().size();i++){
                  RegistB registB = new RegistB();
                  registB.setId(timUserSearchSucc.getInfoList().get(i).getIdentifier());
                  user.add(registB);
              }
                onFinishListener.onFinish(null);
            }
        });

        TIMFriendshipManager.getInstance().getUsersProfile(Collections.singletonList(key), new TIMValueCallBack<List<TIMUserProfile>>() {
            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onSuccess(List<TIMUserProfile> profile) {
                for(int i=0;i<profile.size();i++){
                    RegistB registB = new RegistB();
                    registB.setId(profile.get(i).getIdentifier());
                    user.add(registB);
                }
                onFinishListener.onFinish(null);
            }
        });
    }

    public void addFriend(final String id,String remark,String group,String message){
        List<TIMAddFriendRequest> reqList = new ArrayList<>();
        TIMAddFriendRequest req = new TIMAddFriendRequest(id);
        req.setAddWording(message);
        req.setRemark(remark);
        req.setFriendGroup(group);
        reqList.add(req);
        TIMFriendshipManagerExt.getInstance().addFriend(reqList, new TIMValueCallBack<List<TIMFriendResult>>() {

            @Override
            public void onError(int arg0, String arg1) {
                LogUtil.E("");
            }

            @Override
            public void onSuccess(List<TIMFriendResult> arg0) {
                LogUtil.E("");
            }

        });
    }

    public void delFriend(final String id){
        TIMFriendshipManagerExt.DeleteFriendParam param = new TIMFriendshipManagerExt.DeleteFriendParam();
        param.setType(TIMDelFriendType.TIM_FRIEND_DEL_BOTH);
        param.setUsers(Collections.singletonList(id));

        TIMFriendshipManagerExt.getInstance().delFriend(param, new TIMValueCallBack<List<TIMFriendResult>>() {
            @Override
            public void onError(int i, String s) {
                int a=0;
            }

            @Override
            public void onSuccess(List<TIMFriendResult> timFriendResults) {
                for (TIMFriendResult item : timFriendResults) {
                    if (item.getIdentifer().equals(id)) {

                    }
                }
            }
        });
    }

}
