package com.github.chenyoca.validation.supported;

import android.text.TextUtils;

/**
 * AUTH: chenyoca (chenyoca@gmail.com)
 * DATE: 2014-06-25
 * Not blank runner
 */
public class NotBlankRunner extends TestRunner{

    public NotBlankRunner(){
        super("输入的内容不能为任何空值！");
    }


    @Override
    public boolean test(CharSequence inputValue) {
        boolean empty = TextUtils.isEmpty(inputValue);
        return ! empty && ! match("^\\s*$", inputValue);
    }
}
