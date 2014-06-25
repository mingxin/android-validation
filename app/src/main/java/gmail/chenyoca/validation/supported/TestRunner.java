package gmail.chenyoca.validation.supported;

import java.util.regex.Pattern;

/**
 * AUTH: chenyoca (chenyoca@gmail.com)
 * DATE: 2014-06-25
 * Test runner.
 */
public abstract class TestRunner {

    public abstract boolean test(CharSequence inputValue);

    protected static boolean match(String regex, CharSequence inputValue){
        Pattern p = Pattern.compile(regex);
        return p.matcher(inputValue).matches();
    }

}
