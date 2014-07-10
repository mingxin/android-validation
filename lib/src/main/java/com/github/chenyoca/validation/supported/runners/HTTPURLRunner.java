package com.github.chenyoca.validation.supported.runners;

import android.content.Context;

import com.github.chenyoca.validation.R;
import com.github.chenyoca.validation.supported.TestRunner;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * HTTP URL
 */
public class HTTPURLRunner extends TestRunner{

    static final String URL_REGEX =
            "^(https?:\\/\\/)?[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?$";

    public HTTPURLRunner(Context c){
        super(c, R.string.http_url);
    }


    @Override
    public boolean test(CharSequence inputValue) {
        return match(URL_REGEX, inputValue);
    }

}
