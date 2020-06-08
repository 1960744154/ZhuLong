package com.lhr.zhulong.interfaces;

import android.text.Editable;
import android.text.TextWatcher;


public abstract class MyTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        onMyTextChanged(charSequence, i, i1, i2);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public abstract void onMyTextChanged(CharSequence charSequence, int i, int i1, int i2);
}
