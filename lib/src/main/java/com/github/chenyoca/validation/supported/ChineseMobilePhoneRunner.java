package com.github.chenyoca.validation.supported;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Chinese mobile phone
 */
public class ChineseMobilePhoneRunner extends TestRunner{

    static final String PHONE_REGEX = "^(\\+?\\d{2}-?)?(1[0-9])\\d{9}$";

    public ChineseMobilePhoneRunner(){
        super("请输入有效的手机号码！");
    }

    @Override
    public boolean test(CharSequence inputValue) {
        return match(PHONE_REGEX, inputValue);
    }

}
