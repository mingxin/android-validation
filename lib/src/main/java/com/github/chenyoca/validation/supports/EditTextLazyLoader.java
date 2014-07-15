package com.github.chenyoca.validation.supports;

import android.widget.EditText;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-15
 */
public class EditTextLazyLoader extends StringLazyLoader {

    private EditText editText;

    public EditTextLazyLoader(EditText editText){
        this.editText = editText;
    }

    @Override
    public String[] stringValues() {
        return new String[]{editText.getText().toString()};
    }
}
