package gmail.chenyoca.validation.supported.runners;

import android.content.Context;

import gmail.chenyoca.validation.R;
import gmail.chenyoca.validation.supported.TestRunner;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Length in min
 */
public class LengthInMinRunner extends TestRunner{

    public LengthInMinRunner(Context c){
        super(c, R.string.length_in_min);
    }

    @Override
    public boolean test(CharSequence inputValue) {
        if (usingType != USING_INT) throw new IllegalArgumentException("LengthInRange Test ONLY accept int parameters!");
        if (message != null) message = String.format(message, iValue1);
        int minLength = iValue1;
        return inputValue.length() >= minLength;
    }

}
