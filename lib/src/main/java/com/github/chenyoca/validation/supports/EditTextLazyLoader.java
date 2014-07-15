package com.github.chenyoca.validation.supports;

import android.view.View;
import android.widget.EditText;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-15
 */
public class EditTextLazyLoader extends StringLazyLoader {

    private final EditText editText;

    public EditTextLazyLoader(EditText editText){
        this.editText = editText;
    }

    public EditTextLazyLoader(View parent, int viewId){
        this((EditText)parent.findViewById(viewId));
    }

    @Override
    public String[] stringValues() {
        return new String[]{editText.getText().toString()};
    }
}
