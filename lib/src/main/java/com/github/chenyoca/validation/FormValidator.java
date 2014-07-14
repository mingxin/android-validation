package com.github.chenyoca.validation;

import android.text.InputType;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.github.chenyoca.validation.supported.ChineseMobilePhoneRunner;
import com.github.chenyoca.validation.supported.CreditCardRunner;
import com.github.chenyoca.validation.supported.DigitsRunner;
import com.github.chenyoca.validation.supported.EmailRunner;
import com.github.chenyoca.validation.supported.HTTPURLRunner;
import com.github.chenyoca.validation.supported.HostRunner;
import com.github.chenyoca.validation.supported.IPv4Runner;
import com.github.chenyoca.validation.supported.LengthInMaxRunner;
import com.github.chenyoca.validation.supported.LengthInRangeRunner;
import com.github.chenyoca.validation.supported.NumericRunner;
import com.github.chenyoca.validation.supported.RequiredRunner;
import com.github.chenyoca.validation.supported.TestRunner;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-25
 * Test all children which is EditText in Layout.
 */
public class FormValidator {

    private String message;
    private Display display;
    private ViewGroup form;

    private SparseArray<String> values = new SparseArray<String>();

    public String getMessage(){
        return message;
    }

    private SparseArray<Config> configs = new SparseArray<Config>();

    public FormValidator(){
        this(new Display() {
            @Override
            public void dismiss(EditText field) {
                field.setError(null);
            }

            @Override
            public void show(EditText field, String message) {
                field.setError(message);
            }
        });
    }

    public FormValidator(Display display){
        this.display = display;
    }

    /**
     * Add a test field with types and view id.
     * @param viewId View id for the test field.
     * @param types Types
     * @return FormValidator instance.
     */
    public FormValidator addField(int viewId,Types...types){
        if (types.length < 1) throw new IllegalArgumentException("Types array at less 1 parameter !");
        Config s = new Config();
        for (Types t : types){
            s.add(t);
        }
        configs.append(viewId, s);
        return this;
    }

    /**
     * Add a test field with config and view id.
     * @param viewId View id for the test field.
     * @param config Config
     * @return FormValidator instance.
     */
    public FormValidator addField(int viewId, Config config){
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
        checkBindForm();
        int childrenCount = form.getChildCount();
        for (int i = 0; i < childrenCount; i++){
            View c = form.getChildAt(i);
            if (c instanceof EditText){
                EditText item = (EditText) c;
                Config conf = configs.get(item.getId());
                if (conf == null) continue;
                int inputType = InputType.TYPE_CLASS_TEXT;
                for (TestRunner r : conf.runners){
                    if (r instanceof ChineseMobilePhoneRunner){
                        inputType |= InputType.TYPE_CLASS_PHONE;
                    }else if (r instanceof CreditCardRunner || r instanceof NumericRunner){
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

    /**
     * Test all fields, and get a boolean reset , SHOP test when got a test failed.
     * @return True if passed, false otherwise.
     */
    public boolean test(){
        checkBindForm();
        return testForm(form);
    }

    /**
     * Test all fields, and get a boolean reset.
     * @return True if passed, false otherwise.
     */
    public boolean testAll(){
        checkBindForm();
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
        values.clear();
        for (int i = 0; i < childrenCount; i++){
            View c = form.getChildAt(i);
            if (c instanceof EditText){
                EditText item = (EditText) c;
                int viewId = item.getId();
                Config conf = configs.get(viewId);
                if (conf == null) continue;
                ResultWrapper rs = testField(item, conf, display);
                testPassed &= rs.passed;
                message = rs.message;
                values.put(viewId, rs.value);
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

    public String getValue(int viewId){
        return values.get(viewId);
    }

    /**
     * Set a custom display interface.
     * @param display display interface
     */
    public void setDisplay(Display display){
        this.display = display;
    }

    /**
     * Test edit text field .
     * @param field Input field , a EditText view.
     * @param conf Test configuration .
     * @return Test result wrapper.
     */
    public static ResultWrapper testField(EditText field, Config conf, Display display){
        if (conf == null) return new ResultWrapper(false,"Field configuration CANNOT BE NULL !!", null);
        boolean passed = true;
        String message = null;
        CharSequence input = field.getText();
        if (display != null) display.dismiss(field);

        // If required
        TestRunner firstRunner = conf.runners.get(0);
        if (firstRunner instanceof RequiredRunner){
            passed = firstRunner.test(input);
            message = firstRunner.getMessage();
        }else if (TextUtils.isEmpty(input)){
            return new ResultWrapper(true, "NO_INPUT_BUT_NOT_REQUIRED", String.valueOf(input));
        }
        if ( ! passed){
            if (display != null) display.show(field, message);
            return new ResultWrapper(false, message, null);
        }

        for (TestRunner r : conf.runners){
            if (r instanceof RequiredRunner) continue;
            passed = r.test(input);
            message = r.getMessage();
            System.out.println(">> Field test: "+ passed+", msg: "+message);
            if ( !passed){
                if (display != null) display.show(field, message);
                break;
            }
        }
        System.out.println(">> Last test: "+ passed+", msg: "+message);
        return new ResultWrapper(passed, message, String.valueOf(input));
    }

    private void checkBindForm(){
        if (form == null) throw new IllegalStateException("Form is NULL ! Call 'bind(form)' First !");
    }
}
