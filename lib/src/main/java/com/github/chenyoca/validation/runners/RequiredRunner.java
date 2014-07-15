package com.github.chenyoca.validation.runners;

import android.text.TextUtils;

/**
 * AUTH: chenyoca (chenyoca@gmail.com)
 * DATE: 2014-06-25
 * Required runner.
 */
public class RequiredRunner extends TestRunner{

    public RequiredRunner(){
        super("必填选项！");
    }

    @Override
    public boolean test(CharSequence inputValue) {
        return ! TextUtils.isEmpty(inputValue);
    }
}
