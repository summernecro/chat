package com.summer.chat.main.main;

//by summer on 2018-07-09.

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.ope.BaseValue;
import com.android.lib.view.bottommenu.BottomMenuBean;
import com.summer.chat.R;
import com.summer.chat.main.conversation.ConversationFrag;
import com.summer.chat.main.community.CommunityFrag;
import com.summer.chat.main.contact.ContactFrag;
import com.summer.chat.main.setting.SettingFrag;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainValue extends BaseValue{

    public static final String 记录 = "记录";

    public static final String 通讯 = "通讯";

    public static final String 社区 = "社区";

    public static final String 设置 = "设置";

    public static final int 记录ID  = R.id.contain_chat;

    public static final int 通讯ID = R.id.contain_contact;

    public static final int 社区ID = R.id.contain_community;

    public static final int 设置ID = R.id.contain_setting;

    public static final String[] 模块 = new String[]{记录,通讯,社区,设置};

    public static final int[] 模块ID = new int[]{记录ID,通讯ID,社区ID,设置ID};

    private ArrayList<BaseUIFrag> fragments = new ArrayList<>();

    private ArrayList<BottomMenuBean> bottomMenuBeans = new ArrayList<>();

    private int pos = 0;

    public MainValue(){
        fragments.add(new ConversationFrag());
        fragments.add(new ContactFrag());
        fragments.add(new CommunityFrag());
        fragments.add(new SettingFrag());
    }

    public BaseUIFrag getCurrent(){
        return fragments.get(pos);
    }
}
