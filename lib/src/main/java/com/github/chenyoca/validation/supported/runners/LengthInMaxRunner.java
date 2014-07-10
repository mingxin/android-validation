package com.github.chenyoca.validation.supported.runners;

import android.content.Context;

import com.github.chenyoca.validation.R;
import com.github.chenyoca.validation.supported.TestRunner;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Length in max
 */
public class LengthInMaxRunner extends TestRunner{

    public LengthInMaxRunner(Context c){
        super(c, R.string.length_in_max);
    }

    @Override
    public boolean test(CharSequence inputValue) {
        if (usingType != USING_INT) throw new IllegalArgumentException("LengthInRange Test ONLY accept int parameters!");
        if (message != null) message = String.format(message, iValue1);
        int maxLength = iValue1;
        return inputValue.length() <= maxLength;
    }

}
