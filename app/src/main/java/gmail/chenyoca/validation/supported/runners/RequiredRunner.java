package gmail.chenyoca.validation.supported.runners;

import android.text.TextUtils;

import gmail.chenyoca.validation.supported.TestRunner;

/**
 * AUTH: chenyoca (chenyoca@gmail.com)
 * DATE: 2014-06-25
 * Required runner.
 */
public class RequiredRunner extends TestRunner{

    @Override
    public boolean test(CharSequence inputValue) {
        return TextUtils.isEmpty(inputValue);
    }
}
