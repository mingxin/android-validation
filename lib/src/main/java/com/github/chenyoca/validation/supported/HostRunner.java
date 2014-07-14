package com.github.chenyoca.validation.supported;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Host
 */
public class HostRunner extends IPv4Runner{

    static final String HOST_REGEX = "^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,65}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}$";


    public HostRunner(){
        super("请输入有效的主机地址！");
    }

    @Override
    public boolean test(CharSequence inputValue) {
        return super.test(inputValue) || match(HOST_REGEX, inputValue);
    }

}
