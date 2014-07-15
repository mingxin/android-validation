package com.github.chenyoca.validation.runners;

import android.text.TextUtils;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Only digits
 */
public class DigitsRunner extends TestRunner{


    public DigitsRunner(){
        super("只能输入数字！");
    }

    @Override
    public boolean test(CharSequence inputValue) {
        return TextUtils.isDigitsOnly(inputValue);
    }

}
