package gmail.chenyoca.validation;

import android.text.InputType;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import gmail.chenyoca.validation.supported.TestRunner;
import gmail.chenyoca.validation.supported.runners.ChineseMobilePhoneRunner;
import gmail.chenyoca.validation.supported.runners.CreditCardRunner;
import gmail.chenyoca.validation.supported.runners.DigitsRunner;
import gmail.chenyoca.validation.supported.runners.EmailRunner;
import gmail.chenyoca.validation.supported.runners.HTTPURLRunner;
import gmail.chenyoca.validation.supported.runners.HostRunner;
import gmail.chenyoca.validation.supported.runners.IPv4Runner;
import gmail.chenyoca.validation.supported.runners.LengthInMaxRunner;
import gmail.chenyoca.validation.supported.runners.LengthInRangeRunner;
import gmail.chenyoca.validation.supported.runners.NumericRunner;
import gmail.chenyoca.validation.supported.runners.RequiredRunner;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-25
 * Test all children which is EditText in Layout.
 */
public class FormValidator {

    private String message;

    private ViewGroup form;

    public String getMessage(){
        return message;
    }

    private SparseArray<Configuration> configs = new SparseArray<Configuration>();

    public FormValidator configFor(Configuration config, int viewId){
        configs.append(viewId, config);
        return this;
    }

    /**
     * Bind a form for action
     * @param form Target form layout
     * @return FormValidator instance.
     */
    public FormValidator bind(ViewGroup form){
        this.form = form;
        return this;
    }

    /**
     * Apply InputType to EditText.
     */
    public FormValidator applyTypeToView(){
        int childrenCount = form.getChildCount();
        for (int i = 0; i < childrenCount; i++){
            View c = form.getChildAt(i);
            if (c instanceof EditText){
                EditText item = (EditText) c;
                Configuration conf = configs.get(item.getId());
                if (conf == null) continue;
                int inputType = 0;
                for (TestRunner r : conf.runners){
                    if (r instanceof ChineseMobilePhoneRunner){
                        inputType |= InputType.TYPE_CLASS_PHONE;
                    }
                    if (r instanceof CreditCardRunner || r instanceof NumericRunner){
                        inputType |= InputType.TYPE_CLASS_NUMBER;
                    }
                    if (r instanceof DigitsRunner){
                        inputType |= InputType.TYPE_NUMBER_FLAG_SIGNED;
                    }
                    if (r instanceof EmailRunner){
                        inputType |= InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
                    }
                    if (r instanceof HostRunner || r instanceof HTTPURLRunner || r instanceof IPv4Runner){
                        inputType |= InputType.TYPE_TEXT_VARIATION_URI;
                    }
                    //
                    if (r instanceof LengthInMaxRunner){
                        item.setMaxHeight(r.iValue1);
                    }else if (r instanceof LengthInRangeRunner){
                        item.setMaxHeight(r.iValue2);
                    }
                }
                item.setInputType(inputType);
            }
        }
        return this;
    }

    public boolean test(){
        if (form == null) throw new IllegalStateException("Form is NULL ! Call 'bind(form)' First !");
        return testForm(form);
    }

    public boolean testAll(){
        if (form == null) throw new IllegalStateException("Form is NULL ! Call 'bind(form)' First !");
        return testFormAll(form);
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
