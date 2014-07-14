package com.github.chenyoca.validation.supported;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Equal to a value
 */
public class EqualToRunner extends TestRunner{

    public EqualToRunner(){
        super("请输入相同的内容！");
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
