package gmail.chenyoca.validation.supported.runners;

import android.content.Context;

import gmail.chenyoca.validation.R;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Host
 */
public class HostRunner extends IPv4Runner{

    static final String HOST_REGEX = "^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,65}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}$";

    public HostRunner(Context c){
        super(c, R.string.host);
    }


    @Override
    public boolean test(CharSequence inputValue) {
        return super.test(inputValue) || match(HOST_REGEX, inputValue);
    }

}
