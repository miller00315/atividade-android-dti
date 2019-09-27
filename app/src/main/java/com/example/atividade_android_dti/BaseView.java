package com.example.atividade_android_dti;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);
}
