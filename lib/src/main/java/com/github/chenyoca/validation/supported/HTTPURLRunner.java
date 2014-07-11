package com.github.chenyoca.validation.supported;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * HTTP URL
 */
public class HTTPURLRunner extends TestRunner{

    static final String URL_REGEX =
            "^(https?:\\/\\/)?[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?$";

    public HTTPURLRunner(){
        super("请输入有效的网址！");
    }

    @Override
    public boolean test(CharSequence inputValue) {
        return match(URL_REGEX, inputValue);
    }

}
