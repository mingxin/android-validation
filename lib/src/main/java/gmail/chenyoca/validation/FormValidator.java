package gmail.chenyoca.validation;

import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import gmail.chenyoca.validation.supported.Configuration;
import gmail.chenyoca.validation.supported.TestRunner;
import gmail.chenyoca.validation.supported.runners.RequiredRunner;

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

    private SparseArray<Configuration> configs = new SparseArray<Configuration>();

    public FormValidator configFor(Configuration config, int viewId){
        configs.append(viewId, config);
        return this;
    }

    /**
     * Test the form layout.
     * @param form The form layout
     * @param continueTest If true, continue when a filed test failed, otherwise break.
     * @return True when test passed .
     */
    private boolean testForm(ViewGroup form, boolean continueTest){
        int childrenCount = form.getChildCount();
        boolean testPassed = true;
        for (int i = 0; i < childrenCount; i++){
            View c = form.getChildAt(i);
            if (c instanceof EditText){
                EditText item = (EditText) c;
                Configuration conf = configs.get(item.getId());
                if (conf == null) continue;
                ResultWrapper rs = testField(item, conf);
                testPassed &= rs.passed;
                message = rs.message;
                if (! continueTest && ! testPassed) break;
            }
        }
        return testPassed;
    }

    public boolean testForm(ViewGroup form){
        return testForm(form, false);
    }

    public boolean testFormAll(ViewGroup form){
        return testForm(form, true);
    }

    /**
     * Test edit text field .
     * @param field Input field , a EditText view.
     * @param conf Test configuration .
     * @return Test result wrapper.
     */
    public static ResultWrapper testField(android.widget.EditText field, Configuration conf){
        if (conf == null) return new ResultWrapper(false,"Field configuration CANNOT BE NULL !!");
        boolean passed = true;
        String message = null;
        CharSequence input = field.getText();

        // If required
        TestRunner firstRunner = conf.runners.get(0);
        if (firstRunner instanceof RequiredRunner){
            passed = firstRunner.test(input);
            message = firstRunner.getMessage();
            field.setError(message);
        }else if (TextUtils.isEmpty(input)){
            return new ResultWrapper(true, "NO_INPUT_BUT_NOT_REQUIRED");
        }

        if ( ! passed) return new ResultWrapper(false, message);

        for (TestRunner r : conf.runners){
            if (r instanceof RequiredRunner) continue;
            if ( ! (passed = r.test(input))){
                message = r.getMessage();
                field.setError(message);
                break;
            }
        }
        return new ResultWrapper(passed, message);
    }
}
