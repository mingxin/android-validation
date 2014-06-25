package gmail.chenyoca.validation;

import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;

import gmail.chenyoca.validation.supported.Configuration;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-25
 * Test all children which is EditText in Layout.
 */
public class FormValidator {

    private String message;

    public String getMessage(){
        return message;
    }

    private boolean test(ViewGroup form, boolean continueTest){
        int childrenCount = form.getChildCount();
        boolean testPassed = true;
        for (int i = 0; i < childrenCount; i++){
            View c = form.getChildAt(i);
            if (c instanceof EditText){
                EditText item = (EditText) c;
                FieldValidator.TestResult rs = FieldValidator.test(item, item.testConf);
                testPassed &= rs.passed;
                message = rs.message;
                if (! continueTest && ! testPassed) break;
            }
        }
        return testPassed;
    }

    public boolean test(ViewGroup form){
        return test(form, false);
    }

    public boolean testAll(ViewGroup form){
        return test(form, true);
    }
}
