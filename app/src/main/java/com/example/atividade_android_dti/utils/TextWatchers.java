package com.example.atividade_android_dti.utils;

import android.text.Editable;
import android.text.TextWatcher;

public class TextWatchers {

    private TextWatchersInterface listener;

    private static TextWatchers textWatchers;

    private TextWatchers() { }

    public static TextWatchers getInstance(){

        if(textWatchers == null)
            textWatchers = new TextWatchers();

        return textWatchers;

    }

    public void setListener(TextWatchersInterface listener){
        this.listener = listener;
    }

    private TextWatcher userNameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            listener.isValidUserName(StringValidator.isValidUserName(editable.toString().trim()));
        }
    };

    private TextWatcher userPasswordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            listener.isValidUserPassword(StringValidator.isValidPassword(editable.toString().trim()));
        }
    };

    public TextWatcher getUserNameTextWatcher() {
        return userNameTextWatcher;
    }

    public TextWatcher getUserPasswordTextWatcher() {
        return userPasswordTextWatcher;
    }
}
