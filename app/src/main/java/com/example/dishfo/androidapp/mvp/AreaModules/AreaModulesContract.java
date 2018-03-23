package com.example.dishfo.androidapp.mvp.AreaModules;

import com.example.dishfo.androidapp.bean.AreaInfo;
import com.example.dishfo.androidapp.bean.NoteInfo;
import com.example.dishfo.androidapp.mvp.BaseModel;
import com.example.dishfo.androidapp.mvp.BasePresenter;
import com.example.dishfo.androidapp.mvp.BaseView;
import com.example.dishfo.androidapp.sqlBean.Like;
import com.example.dishfo.androidapp.sqlBean.Note;
import com.example.dishfo.androidapp.viewBean.ViewArea;
import com.example.dishfo.androidapp.viewBean.ViewNote;

import java.util.List;

/**
 * Created by dishfo on 18-3-6.
 */

public class AreaModulesContract {
    public static final int FOLLOW=0X44;
    public static final int AREA=0X1;
    public static final int NOTE=0X2;
    public static final int SUCCEED=0X4;
    public static final int FAILED=0X5;

    public interface AreaModulesPresent extends BasePresenter {
        public void onFollowArea(ViewArea viewArea);
    }

    public interface AreaModulesView extends BaseView<AreaModulesPresent>{
        public void showNotes(List<ViewNote> viewNotes);
        public void showArea(ViewArea area);
        public void onFollowArea(ViewArea area);
    }

    public interface  AreaModulesModel extends BaseModel<AreaModulesPresent>{
        public void getAreaWithNotes(String name);
        public void FollowArea(ViewArea viewArea);
    }

}