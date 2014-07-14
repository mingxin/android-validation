package com.github.chenyoca.validation.supported;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Email
 */
public class EmailRunner extends TestRunner{

    static final String EMAIL_REGEX =
            "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
                    "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" +
                    "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";

    public EmailRunner(){
        super("请输入有效的邮件地址！");
    }

    @Override
    public boolean test(CharSequence inputValue) {
        return match(EMAIL_REGEX, inputValue);
    }

}
