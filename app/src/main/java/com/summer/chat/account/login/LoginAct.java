package com.summer.chat.account.login;

//by summer on 2018-07-09.

import android.view.View;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.util.LogUtil;
import com.summer.chat.R;
import com.summer.chat.account.regist.RegistAct;
import com.summer.chat.chat.chatinit.ChatInit;
import com.summer.chat.main.main.MainAct;
import com.tencent.imsdk.TIMCallBack;

import butterknife.OnClick;
import butterknife.Optional;
import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSPwdLoginListener;
import tencent.tls.platform.TLSUserInfo;

public class LoginAct extends BaseUIActivity<LoginUIOpe,LoginDAOpe,LoginValue> implements View.OnClickListener{

    @Override
    protected void initNow() {
        super.initNow();

    }

    @Optional
    @OnClick({R.id.btn_login,R.id.registerNewUser})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                if(getP().getU().initRegistB(getP().getV().getRegistB())){
                    getP().getD().login(getP().getV().getRegistB(), new TLSPwdLoginListener() {
                        @Override
                        public void OnPwdLoginSuccess(TLSUserInfo tlsUserInfo) {
                            LogUtil.E(tlsUserInfo);
                            getP().getV().getRegistB().setId(ChatInit.getInstance().getLoginHelper().getLastUserInfo().identifier);
                            getP().getV().getRegistB().setUserSig(ChatInit.getInstance().getLoginHelper().getUserSig(getP().getV().getRegistB().getId()));
                            ChatInit.getInstance().sigLogin(getP().getV().getRegistB().getId(), getP().getV().getRegistB().getUserSig(), new TIMCallBack() {
                                @Override
                                public void onError(int i, String s) {
                                    LogUtil.E("");
                                }

                                @Override
                                public void onSuccess() {
                                    LogUtil.E("");
                                    getActivity().finish();
                                    MainAct.goTo(getActivity());
                                }
                            });
                        }

                        @Override
                        public void OnPwdLoginReaskImgcodeSuccess(byte[] bytes) {
                            LogUtil.E(bytes);
                        }

                        @Override
                        public void OnPwdLoginNeedImgcode(byte[] bytes, TLSErrInfo tlsErrInfo) {
                            LogUtil.E(tlsErrInfo);
                        }

                        @Override
                        public void OnPwdLoginFail(TLSErrInfo tlsErrInfo) {
                            LogUtil.E(tlsErrInfo);
                        }

                        @Override
                        public void OnPwdLoginTimeout(TLSErrInfo tlsErrInfo) {
                            LogUtil.E(tlsErrInfo);
                        }
                    });
                }
                break;
            case R.id.registerNewUser:
                RegistAct.goTo(getActivity());
                break;
        }
    }
}
