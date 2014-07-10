package com.github.chenyoca.validation.supported.runners;

import android.content.Context;

import com.github.chenyoca.validation.R;
import com.github.chenyoca.validation.supported.TestRunner;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * IPv4
 */
public class IPv4Runner extends TestRunner{

    static final String IPV4_REGEX = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

    public IPv4Runner(Context c){
        super(c, R.string.ipv4);
    }

    // For host runner
    public IPv4Runner(Context c,int msgResId){
        super(c, msgResId);
    }

    @Override
    public boolean test(CharSequence inputValue) {
        return match(IPV4_REGEX, inputValue);
    }

}
