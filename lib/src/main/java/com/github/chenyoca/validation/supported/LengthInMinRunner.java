package com.github.chenyoca.validation.supported;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Length in min
 */
public class LengthInMinRunner extends TestRunner{

    public LengthInMinRunner(){
        super("请输入长度大于%d的内容！");
    }

    @Override
    public boolean test(CharSequence inputValue) {
        if (usingType != USING_INT) throw new IllegalArgumentException("LengthInRange Test ONLY accept int parameters!");
        if (message != null) message = String.format(message, iValue1);
        int minLength = iValue1;
        return inputValue.length() >= minLength;
    }

}
