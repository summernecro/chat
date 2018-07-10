package com.summer.chat.main.setting;

//by summer on 2018-07-09.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.util.LogUtil;
import com.summer.chat.R;
import com.summer.chat.chat.chatinit.ChatInit;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;

import butterknife.OnClick;
import butterknife.Optional;

public class SettingFrag extends BaseUIFrag<SettingUIOpe,SettingDAOpe,SettingValue> {

    @Override
    public void initNow() {
        super.initNow();
    }


    @Optional
    @OnClick({R.id.tv_quit})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_quit:
                TIMManager.getInstance().getLoginUser();
                ChatInit.getInstance().logout(new TIMCallBack() {
                    @Override
                    public void onError(int i, String s) {
                        LogUtil.E("");
                    }

                    @Override
                    public void onSuccess() {
                        LogUtil.E("");
                        getActivity().finish();
                    }
                });
                break;
        }
    }
}
