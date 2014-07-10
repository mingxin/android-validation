package com.github.chenyoca.validation.supported.runners;

import android.content.Context;
import android.text.TextUtils;

import com.github.chenyoca.validation.R;
import com.github.chenyoca.validation.supported.TestRunner;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Only digits
 */
public class DigitsRunner extends TestRunner{

    public DigitsRunner(Context c){
        super(c, R.string.digits);
    }

    @Override
    public boolean test(CharSequence inputValue) {
        return TextUtils.isDigitsOnly(inputValue);
    }

}
