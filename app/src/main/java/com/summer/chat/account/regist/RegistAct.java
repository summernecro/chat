package com.summer.chat.account.regist;

//by summer on 2018-07-09.

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.util.LogUtil;
import com.android.lib.util.ToastUtil;
import com.summer.chat.R;
import com.summer.chat.chat.chatinit.ChatInit;

import butterknife.OnClick;
import butterknife.Optional;
import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSStrAccRegListener;
import tencent.tls.platform.TLSUserInfo;

public class RegistAct extends BaseUIActivity<RegistUIOpe,RegistDAOpe,RegistValue> implements View.OnClickListener{

    public static void goTo(Context context){
        context.startActivity(new Intent(context,RegistAct.class));
    }

    @Optional
    @OnClick({R.id.btn_regist})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_regist:
                if(getP().getU().initRegistB(getP().getV().getRegistB())){
                    getP().getD().regist(getP().getV().getRegistB(), new TLSStrAccRegListener() {
                        @Override
                        public void OnStrAccRegSuccess(TLSUserInfo tlsUserInfo) {
                            ToastUtil.getInstance().showShort(getActivity(),"注册成功");
                            finish();
                        }

                        @Override
                        public void OnStrAccRegFail(TLSErrInfo tlsErrInfo) {
                            LogUtil.E(tlsErrInfo);
                        }

                        @Override
                        public void OnStrAccRegTimeout(TLSErrInfo tlsErrInfo) {
                            LogUtil.E(tlsErrInfo);
                        }
                    });
                }
                break;
        }
    }
}
