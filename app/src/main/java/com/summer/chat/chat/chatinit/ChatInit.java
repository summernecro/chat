package com.summer.chat.chat.chatinit;

//by summer on 2018-07-09.

import android.content.Context;
import android.os.Environment;


import com.android.lib.util.LogUtil;
import com.android.lib.util.ToastUtil;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMFriendshipSettings;
import com.tencent.imsdk.TIMGroupEventListener;
import com.tencent.imsdk.TIMGroupMemberInfo;
import com.tencent.imsdk.TIMGroupSettings;
import com.tencent.imsdk.TIMGroupTipsElem;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMLogListener;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMRefreshListener;
import com.tencent.imsdk.TIMSNSChangeInfo;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMUserStatusListener;
import com.tencent.imsdk.ext.group.TIMGroupAssistantListener;
import com.tencent.imsdk.ext.group.TIMGroupCacheInfo;
import com.tencent.imsdk.ext.group.TIMUserConfigGroupExt;
import com.tencent.imsdk.ext.message.TIMUserConfigMsgExt;
import com.tencent.imsdk.ext.sns.TIMFriendshipManagerExt;
import com.tencent.imsdk.ext.sns.TIMFriendshipProxyListener;
import com.tencent.imsdk.ext.sns.TIMUserConfigSnsExt;
import com.tencent.qalsdk.QALSDKManager;

import java.util.List;

import tencent.tls.platform.TLSAccountHelper;
import tencent.tls.platform.TLSLoginHelper;
import tencent.tls.platform.TLSPwdLoginListener;
import tencent.tls.platform.TLSStrAccRegListener;

public class ChatInit {

    private static ChatInit chatInit;

    private TLSLoginHelper loginHelper;
    private TLSAccountHelper accountHelper;

    public static ChatInit getInstance(){
        if(chatInit==null){
            chatInit = new ChatInit();
        }
        return chatInit;
    }

    public void init(Context context){
        QALSDKManager.getInstance().init(context.getApplicationContext(), ChatInitValue.SDKAPPID);
        QALSDKManager.getInstance().setEnv(ChatInitValue.正式环境);
        TIMSdkConfig config = new TIMSdkConfig(ChatInitValue.SDKAPPID)
                .enableCrashReport(false).enableLogPrint(true)
                .setLogLevel(TIMLogLevel.DEBUG)
                .setLogListener(new TIMLogListener() {
                    @Override
                    public void log(int i, String s, String s1) {
                        LogUtil.E(i+"---"+s+"---"+s1);
                    }
                })
                .setLogPath(Environment.getExternalStorageDirectory().getPath() + "/justfortest/");
        TIMManager.getInstance().init(context, config);
        TIMManager.getInstance().setEnv(ChatInitValue.正式环境);
        loginHelper = TLSLoginHelper.getInstance().init(context.getApplicationContext(),
                ChatInitValue.SDKAPPID, ChatInitValue.ACCOUNT_TYPE, ChatInitValue.APP_VERSION);
        loginHelper.setTimeOut(ChatInitValue.TIMEOUT);
        loginHelper.setLocalId(ChatInitValue.LANGUAGE_CODE);
        loginHelper.setTestHost("", true);                   // 走sso
//        loginHelper.setTestHost("113.108.64.238", false);      // 不走sso

        accountHelper = TLSAccountHelper.getInstance().init(context.getApplicationContext(),
                ChatInitValue.SDKAPPID, ChatInitValue.ACCOUNT_TYPE, ChatInitValue.APP_VERSION);
        accountHelper.setCountry(Integer.parseInt(ChatInitValue.COUNTRY_CODE)); // 存储注册时所在国家，只须在初始化时调用一次
        accountHelper.setTimeOut(ChatInitValue.TIMEOUT);
        accountHelper.setLocalId(ChatInitValue.LANGUAGE_CODE);
        accountHelper.setTestHost("", true);
    }

    public void pwLogin(String identifier, String password, TLSPwdLoginListener listener){
        loginHelper.TLSPwdLogin(identifier, password.getBytes(), listener);
    }

    public void sigLogin(String identify, String userSig, TIMCallBack callBack){
        if (identify == null || userSig == null) return;
        TIMManager.getInstance().login(identify,userSig, callBack);
    }

    public void regist(String account, String password, TLSStrAccRegListener listener){
        accountHelper.TLSStrAccReg(account, password, listener);
    }

    public void logout(TIMCallBack callBack){
        TIMManager.getInstance().logout(callBack);
    }

    public void setUserConfig(){
        TIMUserConfig userConfig = new TIMUserConfig()
                .setGroupSettings(new TIMGroupSettings())
                .setFriendshipSettings(new TIMFriendshipSettings())
                .setUserStatusListener(new TIMUserStatusListener() {
                    @Override
                    public void onForceOffline() {
                        LogUtil.E("");
                    }

                    @Override
                    public void onUserSigExpired() {
                        LogUtil.E("");
                    }
                })
                .setConnectionListener(new TIMConnListener() {
                    @Override
                    public void onConnected() {
                        LogUtil.E("");
                    }

                    @Override
                    public void onDisconnected(int i, String s) {
                        LogUtil.E("");
                    }

                    @Override
                    public void onWifiNeedAuth(String s) {
                        LogUtil.E("");
                    }
                })
                .setGroupEventListener(new TIMGroupEventListener() {
                    @Override
                    public void onGroupTipsEvent(TIMGroupTipsElem timGroupTipsElem) {
                        LogUtil.E("");
                    }
                })
                .setRefreshListener(new TIMRefreshListener() {
                    @Override
                    public void onRefresh() {
                        LogUtil.E("");
                    }

                    @Override
                    public void onRefreshConversation(List<TIMConversation> list) {
                        LogUtil.E("");
                    }
                });
        //消息扩展用户配置
        userConfig = new TIMUserConfigMsgExt(userConfig)
                //禁用消息存储
                .enableStorage(false)
                //开启消息已读回执
                .enableReadReceipt(true);
        userConfig = new TIMUserConfigSnsExt(userConfig)
                .enableFriendshipStorage(true)
                .setFriendshipProxyListener(new TIMFriendshipProxyListener() {
                    @Override
                    public void OnAddFriends(List<TIMUserProfile> list) {
                        LogUtil.E("");
                    }

                    @Override
                    public void OnDelFriends(List<String> list) {
                        LogUtil.E("");
                    }

                    @Override
                    public void OnFriendProfileUpdate(List<TIMUserProfile> list) {
                        LogUtil.E("");
                    }

                    @Override
                    public void OnAddFriendReqs(List<TIMSNSChangeInfo> list) {
                        LogUtil.E("");
                    }
                });
        userConfig = new TIMUserConfigGroupExt(userConfig)
                .enableGroupStorage(true)
                .setGroupAssistantListener(new TIMGroupAssistantListener() {
                    @Override
                    public void onMemberJoin(String s, List<TIMGroupMemberInfo> list) {
                        LogUtil.E("");
                    }

                    @Override
                    public void onMemberQuit(String s, List<String> list) {
                        LogUtil.E("");
                    }

                    @Override
                    public void onMemberUpdate(String s, List<TIMGroupMemberInfo> list) {
                        LogUtil.E("");
                    }

                    @Override
                    public void onGroupAdd(TIMGroupCacheInfo timGroupCacheInfo) {
                        LogUtil.E("");
                    }

                    @Override
                    public void onGroupDelete(String s) {
                        LogUtil.E("");
                    }

                    @Override
                    public void onGroupUpdate(TIMGroupCacheInfo timGroupCacheInfo) {
                        LogUtil.E("");
                    }
                });
        TIMManager.getInstance().setUserConfig(userConfig);


    }


    public void addMessageListener(final Context context){
        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {
            @Override
            public boolean onNewMessages(List<TIMMessage> msgs) {
                for(int i=0;i<msgs.size();i++){
                    for(int j=0;j<msgs.get(i).getElementCount();j++){
                        TIMElem timElem = msgs.get(i).getElement(j);
                        if(timElem.getType()== TIMElemType.Text){
                            TIMTextElem timTextElem = (TIMTextElem) timElem;
                            ToastUtil.getInstance().showShort(context,timTextElem.getText());
                        }
                    }
                }
                return true;
            }
        });
    }

    public TLSLoginHelper getLoginHelper() {
        return loginHelper;
    }

    public TLSAccountHelper getAccountHelper() {
        return accountHelper;
    }
}
