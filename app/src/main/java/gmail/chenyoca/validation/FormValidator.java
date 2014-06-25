package gmail.chenyoca.validation;

import android.view.View;
import android.view.ViewGroup;

import gmail.chenyoca.validation.supported.Configuration;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-25
 * Test all children which is EditText in Layout.
 */
public class FormValidator {

    static class TestResult{

        final boolean passed;
        final String message;

        public TestResult(boolean passed, String message){
            this.passed = passed;
            this.message = message;
        }
    }

    private String message;

    public String getMessage(){
        return message;
    }

    /**
     * Test edit text field .
     * @param field Input field , a EditText view.
     * @param conf Test configuration .
     * @return Test result wrapper.
     */
    public static TestResult test(android.widget.EditText field, Configuration conf){
        if (conf == null) return new TestResult(false,"Field test configuration CAN NOT BE NULL !!");
        boolean flag;
        if ( flag = conf.runner.test(field.getText())){
            field.setError(conf.message);
        }
        return new TestResult(flag, flag ? null : conf.message);
    }

    /**
     * Test the form layout
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
                Configuration conf = Configuration.configFrom(item);
                if (conf == null) continue;
                TestResult rs = test(item, conf);
                testPassed &= rs.passed;
                message = rs.message;
                if (! continueTest && ! testPassed) break;
            }
        }
        return testPassed;
    }

    public boolean test(ViewGroup form){
        return testForm(form, false);
    }

    public boolean testAll(ViewGroup form){
        return testForm(form, true);
    }
}
