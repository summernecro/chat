package com.summer.chat.main.contact;

//by summer on 2018-07-09.

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.android.lib.BR;
import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.summer.chat.R;
import com.summer.chat.account.regist.RegistB;
import com.summer.chat.databinding.DialogAddBinding;
import com.summer.chat.databinding.FragMainContactBinding;
import com.tencent.imsdk.TIMUserProfile;

import java.util.List;

public class ContactUIOpe extends BaseUIOpe<FragMainContactBinding>{

    ;

    public void showAddDialog(){
        Dialog dialog = new Dialog(getActivity(), R.style.dialog);
        DialogAddBinding dialogAddBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()),R.layout.dialog_add,null,false);
        dialog.setContentView(dialogAddBinding.getRoot());
        dialogAddBinding.addFriend.setOnClickListener(getFrag());
        Window window = dialog.getWindow();
        window.setGravity(Gravity.TOP | Gravity.RIGHT);
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setAttributes(lp);
        dialog.show();
    }

    public void initContact(List<RegistB> registBS, ViewListener listener){
        if(getBind().recycle.getAdapter()==null){
            getBind().recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
            getBind().recycle.setAdapter(new AppsDataBindingAdapter(getActivity(),R.layout.item_input_text, BR.item_input_text,registBS,listener));
        }else{
            getBind().recycle.getAdapter().notifyDataSetChanged();
        }
    }

}
