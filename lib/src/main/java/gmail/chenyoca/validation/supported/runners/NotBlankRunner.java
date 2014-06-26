package gmail.chenyoca.validation.supported.runners;

import android.content.Context;
import android.text.TextUtils;

import gmail.chenyoca.validation.R;
import gmail.chenyoca.validation.supported.TestRunner;

/**
 * AUTH: chenyoca (chenyoca@gmail.com)
 * DATE: 2014-06-25
 * Not blank runner
 */
public class NotBlankRunner extends TestRunner{

    public NotBlankRunner(Context c){
        super(c, R.string.not_blank);
    }

    @Override
    public boolean test(CharSequence inputValue) {
        boolean empty = TextUtils.isEmpty(inputValue);
        return ! empty && ! match("^\\s*$", inputValue);
    }
}
