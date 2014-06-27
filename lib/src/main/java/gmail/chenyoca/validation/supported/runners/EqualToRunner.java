package gmail.chenyoca.validation.supported.runners;

import android.content.Context;

import gmail.chenyoca.validation.R;
import gmail.chenyoca.validation.supported.TestRunner;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Equal to a value
 */
public class EqualToRunner extends TestRunner{

    public EqualToRunner(Context c){
        super(c, R.string.equal_to);
    }

    @Override
    public boolean test(CharSequence inputValue) {
        return dispatch(inputValue);
    }

    @Override
    protected boolean testIntValue(int inputValue, int val1, int val2) {
        return inputValue == val1;
    }

    @Override
    protected boolean testDoubleValue(double inputValue, double val1, double val2) {
        return inputValue == val2;
    }

    @Override
    protected boolean testStringValue(String inputValue, String val1, String bal2) {
        return inputValue.equals(val1);
    }
}
