package com.github.chenyoca.validation.supported;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Length in max
 */
public class LengthInMaxRunner extends TestRunner{

    public LengthInMaxRunner(){
        super("请输入长度小于%d的内容！");
    }

    @Override
    public boolean test(CharSequence inputValue) {
        if (usingType != USING_INT) throw new IllegalArgumentException("LengthInRange Test ONLY accept int parameters!");
        if (message != null) message = String.format(message, iValue1);
        int maxLength = iValue1;
        return inputValue.length() <= maxLength;
    }

}
