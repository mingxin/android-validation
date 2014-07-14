package com.github.chenyoca.validation.supported;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Value in range
 */
public class ValueInRangeRunner extends TestRunner{

    public ValueInRangeRunner(){
        super("请输入在[%0f,%0f]的数值！");
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
