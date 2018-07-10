package com.summer.chat.search.addnew;

//by summer on 2018-07-09.

import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.summer.chat.BR;
import com.summer.chat.R;
import com.summer.chat.account.regist.RegistB;
import com.summer.chat.databinding.ActAddnewBinding;

import java.util.ArrayList;

public class AddNewUIOpe extends BaseUIOpe<ActAddnewBinding>{

    @Override
    public void initUI() {
        super.initUI();
        getBind().inputSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_SEARCH){
                    if(getActivity() instanceof OnFinishListener){
                        OnFinishListener onFinishListener = (OnFinishListener) getActivity();
                        onFinishListener.onFinish(v.getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void loadListData(ArrayList<RegistB> users, ViewListener viewListener){
        if(getBind().recycle.getAdapter()==null){
            getBind().recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
            getBind().recycle.setAdapter(new AppsDataBindingAdapter(getActivity(), R.layout.item_input_text, BR.item_input_text,users,viewListener));
        }else{
            getBind().recycle.getAdapter().notifyDataSetChanged();
        }
    }
}
