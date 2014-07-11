package com.github.chenyoca.validation.supported;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Length in range
 */
public class LengthInRangeRunner extends TestRunner{

    public LengthInRangeRunner(){
        super("请输入长度在[%d,%d]之间的内容！");
    }

    @Override
    public boolean test(CharSequence inputValue) {
        if (usingType != USING_INT) throw new IllegalArgumentException("LengthInRange Test ONLY accept int parameters!");
        if (message != null) message = String.format(message, iValue1, iValue2);
        int minLength = iValue1;
        int maxLength = iValue2;
        int length = inputValue.length();
        return minLength <= length && length <= maxLength;
    }

}
