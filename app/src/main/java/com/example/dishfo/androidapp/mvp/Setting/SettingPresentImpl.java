package com.example.dishfo.androidapp.mvp.Setting;

import com.example.dishfo.androidapp.bean.sqlBean.User;

/**
 * Created by dishfo on 18-3-8.
 */

public class SettingPresentImpl implements SettingContract.SettingPresent{

    private SettingContract.SettingModel model;
    private SettingContract.SettingView view;

    public SettingPresentImpl(SettingContract.SettingModel model, SettingContract.SettingView view) {
        this.model = model;
        this.view = view;

        view.setPresent(this);
        model.setPresent(this);
    }

    @Override
    public void start(Object... args) {}

    @Override
    public void stop() {}

    @Override
    public void changeHead(User info, String file) {
        model.toChangeHead(info,file);
    }

    @Override
    public void changeName(User info,String name) {
        model.toChangeName(info,name);
    }

    @Override
    public void onCompete(Object... args) {
        view.compete(args[0],args[1]);
    }

    @Override
    public void onError(Object... args) {
        view.error(args[0]);
    }
}
