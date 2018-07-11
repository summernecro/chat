package com.summer.chat.chat.receipt;

//by summer on 2018-07-10.

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.constant.ValueConstant;
import com.summer.chat.R;
import com.summer.chat.account.regist.RegistB;
import com.summer.chat.chat.chat.ChatAct;
import com.tencent.imsdk.TIMConversationType;

import butterknife.OnClick;

public class ReceiptAct extends BaseUIActivity<ReceiptUIOpe,ReceiptDAOpe,ReceiptValue> implements View.OnClickListener{

    public static void goTo(Context context, RegistB registB){
        Intent i = new Intent(context, ReceiptAct.class);
        i.putExtra(ValueConstant.DATA_DATA,registB);
        context.startActivity(i);
    }

    @Override
    protected void initNow() {
        super.initNow();
        getP().getV().setRegistB((RegistB) getIntent().getSerializableExtra(ValueConstant.DATA_DATA));
        getP().getU().getBind().recordtitle.tvLab.setText(getP().getV().getRegistB().getId());
    }

    @OnClick({R.id.btn_del,R.id.btn_chat})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_del:

                break;
            case R.id.btn_chat:
                ChatAct.goTo(this,getP().getV().getRegistB().getId(), TIMConversationType.C2C);
                break;
        }
    }
}
