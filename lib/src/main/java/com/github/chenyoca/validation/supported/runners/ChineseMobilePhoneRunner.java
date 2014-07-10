package com.github.chenyoca.validation.supported.runners;

import android.content.Context;

import com.github.chenyoca.validation.R;
import com.github.chenyoca.validation.supported.TestRunner;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Chinese mobile phone
 */
public class ChineseMobilePhoneRunner extends TestRunner{

    static final String PHONE_REGEX = "^(\\+?\\d{2}-?)?(1[0-9])\\d{9}$";

    public ChineseMobilePhoneRunner(Context c){
        super(c, R.string.chinese_mobile_phone);
    }

    @Override
    public boolean test(CharSequence inputValue) {
        return match(PHONE_REGEX, inputValue);
    }

}
