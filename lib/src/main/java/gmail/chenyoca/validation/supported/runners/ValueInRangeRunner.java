package gmail.chenyoca.validation.supported.runners;

import android.content.Context;

import gmail.chenyoca.validation.R;
import gmail.chenyoca.validation.supported.TestRunner;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Value in range
 */
public class ValueInRangeRunner extends TestRunner{

    public ValueInRangeRunner(Context c){
        super(c, R.string.length_in_range);
    }

    @Override
    public boolean test(CharSequence inputValue) {
        return dispatch(inputValue);
    }

    @Override
    protected boolean testIntValue(int inputValue, int min, int max) {
        return min <= inputValue && inputValue <= max;
    }

    @Override
    protected boolean testDoubleValue(double inputValue, double min, double max) {
        return min <= inputValue && inputValue <= max;
    }

    @Override
    protected boolean testStringValue(String inputValue, String val1, String bal2) {
        throw new IllegalArgumentException("ValueInRange Test ONLY accept int/double/float parameters!");
    }
}
