package com.example.atividade_android_dti.utils;

import android.text.Editable;
import android.text.TextWatcher;

public class TextWatchers {

    private TextWatchersInterface listener;

    private static TextWatchers TEXT_WATCHER;

    private TextWatchers() { }

    public static TextWatchers getInstance(){

        if(TEXT_WATCHER == null)
            TEXT_WATCHER = new TextWatchers();

        return TEXT_WATCHER;

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
            listener.isValidUserName(StringsHelpers.isValidUserName(editable.toString().trim()));
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
            listener.isValidUserPassword(StringsHelpers.isValidPassword(editable.toString().trim()));
        }
    };

    public void onDestroy(){
        TextWatchers.TEXT_WATCHER.listener = null;
        TextWatchers.TEXT_WATCHER = null;
    }

    public TextWatcher getUserNameTextWatcher() {
        return userNameTextWatcher;
    }

    public TextWatcher getUserPasswordTextWatcher() {
        return userPasswordTextWatcher;
    }

    public interface TextWatchersInterface {

        void isValidUserName(boolean result);

        void isValidUserPassword(boolean result);
    }
}
