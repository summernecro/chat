package com.summer.chat.chat.chat;

//by summer on 2018-07-09.

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.LogUtil;
import com.android.lib.util.ToastUtil;
import com.summer.chat.R;
import com.summer.chat.account.regist.RegistB;
import com.summer.chat.chat.bean.CustomMessage;
import com.summer.chat.chat.bean.Message;
import com.summer.chat.chat.bean.MessageFactory;
import com.summer.chat.chat.bean.TextMessage;
import com.summer.chat.chat.chat.interf.ChatView;
import com.tencent.imsdk.IMLiteBridge;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageStatus;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.message.TIMMessageDraft;
import com.tencent.imsdk.ext.message.TIMMessageLocator;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.OnClick;

public class ChatAct extends BaseUIActivity<ChatUIOpe,ChatDAOpe,ChatValue> implements ChatView {


    public static void goTo(Context context,String identify,TIMConversationType type){
        Intent i = new Intent(context,ChatAct.class);
        i.putExtra(ValueConstant.DATA_DATA,identify);
        i.putExtra(ValueConstant.DATA_DATA2,type);
        context.startActivity(i);
    }

    @Override
    protected void initNow() {
        super.initNow();
        getP().getU().getBind().inputPanel.setChatView(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        getP().getV().setIdentify( getIntent().getStringExtra(ValueConstant.DATA_DATA));
        getP().getV().setType((TIMConversationType) getIntent().getSerializableExtra(ValueConstant.DATA_DATA2));
        getP().getV().setTimConversation(TIMManager.getInstance().getConversation(getP().getV().getType(),getP().getV().getIdentify()));

        getP().getU().initList(getP().getV().getMessageList());

        getP().getD().getMessage(getP().getV().getIsGetingMessage(), getP().getV().getTimConversation(), getP().getV().getMessageList().size() > 0 ? getP().getV().getMessageList().get(0).getMessage() : null, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                List<TIMMessage> timMessages = (List<TIMMessage>) o;
                getP().getU().refreshList(getP().getD().dealMessage(getP().getV().getMessageList(),timMessages));
            }
        });

        getP().getU().initList(getP().getV().getMessageList(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getD().getMessage(getP().getV().getIsGetingMessage(), getP().getV().getTimConversation(), getP().getV().getMessageList().size() > 0 ? getP().getV().getMessageList().get(0).getMessage() : null, new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        List<TIMMessage> timMessages = (List<TIMMessage>) o;
                        getP().getU().refreshList(getP().getD().dealMessage(getP().getV().getMessageList(),timMessages));
                    }
                });
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.btn_send:
//                if(!getP().getU().getText(getP().getV().getText())){
//                    ToastUtil.getInstance().showShort(getBaseContext(),"消息为空");
//                    return;
//                }
//                TIMMessage msg = new TIMMessage();
//                TIMTextElem timTextElem = new TIMTextElem();
//                timTextElem.setText(getP().getV().getText()[0]);
//                timTextElem.getType();
//                if(msg.addElement(timTextElem)!=0){
//                    ToastUtil.getInstance().showShort(getBaseContext(),"not added");
//                    return;
//                }
//                getP().getV().getTimConversation().sendMessage(msg, new TIMValueCallBack<TIMMessage>() {
//                    @Override
//                    public void onError(int i, String s) {
//                        LogUtil.E("failed");
//                        ToastUtil.getInstance().showShort(getBaseContext(),"failed"+s);
//                    }
//
//                    @Override
//                    public void onSuccess(TIMMessage timMessage) {
//                        LogUtil.E("success");
//                        ToastUtil.getInstance().showShort(getBaseContext(),"success");
//                    }
//                });
//                break;
        }
    }

    @Override
    public void showMessage(TIMMessage message) {

    }

    @Override
    public void showMessage(List<Message> messageList, List<TIMMessage> messages) {
        getP().getU().refreshList(getP().getD().dealMessage(messageList,messages));
    }

    @Override
    public void showRevokeMessage(TIMMessageLocator timMessageLocator) {

    }

    @Override
    public void clearAllMessage() {

    }

    @Override
    public void onSendMessageSuccess(TIMMessage message) {

    }

    @Override
    public void onSendMessageFail(int code, String desc, TIMMessage message) {

    }

    @Override
    public void sendImage() {

    }

    @Override
    public void sendPhoto() {

    }

    @Override
    public void sendText() {
        Message message = new TextMessage(getP().getU().getBind().inputPanel.getText());
        getP().getD().sendMessage(getP().getV().getTimConversation(),message.getMessage());
        getP().getU().getBind().inputPanel.setText("");
    }

    @Override
    public void sendFile() {

    }

    @Override
    public void startSendVoice() {

    }

    @Override
    public void endSendVoice() {

    }

    @Override
    public void sendVideo(String fileName) {

    }

    @Override
    public void cancelSendVoice() {

    }

    @Override
    public void sending() {

    }

    @Override
    public void showDraft(TIMMessageDraft draft) {

    }

    @Override
    public void videoAction() {

    }

    @Override
    public void showToast(String msg) {

    }
    @Subscribe
    public void update(List<TIMMessage> msgs){
        getP().getU().refreshList(getP().getD().dealMessage(getP().getV().getMessageList(),msgs));
    }

    @Override
    protected boolean registerEventBus() {
        return true;
    }
}
