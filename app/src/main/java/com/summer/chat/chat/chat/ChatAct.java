package com.summer.chat.chat.chat;

//by summer on 2018-07-09.

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.LogUtil;
import com.android.lib.util.ToastUtil;
import com.summer.chat.R;
import com.summer.chat.account.regist.RegistB;
import com.tencent.imsdk.IMLiteBridge;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMValueCallBack;

import butterknife.OnClick;

public class ChatAct extends BaseUIActivity<ChatUIOpe,ChatDAOpe,ChatValue> {


    public static void goTo(Context context, RegistB registB){
        Intent i = new Intent(context,ChatAct.class);
        i.putExtra(ValueConstant.DATA_DATA,registB);
        context.startActivity(i);
    }

    @Override
    protected void initNow() {
        super.initNow();
        getP().getV().setRegistB((RegistB) getIntent().getSerializableExtra(ValueConstant.DATA_DATA));
        getP().getV().setTimConversation(TIMManager.getInstance().getConversation(TIMConversationType.C2C,getP().getV().getRegistB().getId()));
    }

    @OnClick({R.id.btn_send})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send:
                if(!getP().getU().getText(getP().getV().getText())){
                    ToastUtil.getInstance().showShort(getBaseContext(),"消息为空");
                    return;
                }
                TIMMessage msg = new TIMMessage();
                TIMTextElem timTextElem = new TIMTextElem();
                timTextElem.setText(getP().getV().getText()[0]);
                timTextElem.getType();
                if(msg.addElement(timTextElem)!=0){
                    ToastUtil.getInstance().showShort(getBaseContext(),"not added");
                    return;
                }
                getP().getV().getTimConversation().sendMessage(msg, new TIMValueCallBack<TIMMessage>() {
                    @Override
                    public void onError(int i, String s) {
                        LogUtil.E("failed");
                        ToastUtil.getInstance().showShort(getBaseContext(),"failed"+s);
                    }

                    @Override
                    public void onSuccess(TIMMessage timMessage) {
                        LogUtil.E("success");
                        ToastUtil.getInstance().showShort(getBaseContext(),"success");
                    }
                });
                break;
        }
    }
}
