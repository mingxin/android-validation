package com.github.chenyoca.validation;

import android.text.InputType;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.github.chenyoca.validation.runners.ChineseMobilePhoneRunner;
import com.github.chenyoca.validation.runners.CreditCardRunner;
import com.github.chenyoca.validation.runners.DigitsRunner;
import com.github.chenyoca.validation.runners.EmailRunner;
import com.github.chenyoca.validation.runners.HTTPURLRunner;
import com.github.chenyoca.validation.runners.HostRunner;
import com.github.chenyoca.validation.runners.IPv4Runner;
import com.github.chenyoca.validation.runners.LengthInMaxRunner;
import com.github.chenyoca.validation.runners.LengthInRangeRunner;
import com.github.chenyoca.validation.runners.NumericRunner;
import com.github.chenyoca.validation.runners.RequiredRunner;
import com.github.chenyoca.validation.runners.TestRunner;

/**
 * User: YooJia.Chen@gmail.com
 * Date: 2014-06-25
 */
public class AndroidValidator {

    private String message;
    private Display display;
    private ViewGroup form;

    private SparseArray<String> values = new SparseArray<String>();

    public String getMessage(){
        return message;
    }

    private SparseArray<Config> configs = new SparseArray<Config>();

    public AndroidValidator(){
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

    public AndroidValidator(Display display){
        this.display = display;
    }

    /**
     * Add test fields by types and view id.
     * @param viewId View id for the test field.
     * @param types Build in types
     * @return AndroidValidator instance.
     */
    public AndroidValidator putField(int viewId, Types... types){
        if (types.length < 1) throw new IllegalArgumentException("Types array at less 1 parameter !");
        Config s = Config.build(types[0]).commit();
        for (int i=1;i<types.length;i++){
            s.add(types[i]).commit();
        }
        configs.put(viewId, s);
        return this;
    }

    /**
     * Add a test field with config and view id.
     * @param viewId View id for the test field.
     * @param config Config
     * @return AndroidValidator instance.
     */
    public AndroidValidator putField(int viewId, Config config){
        configs.put(viewId, config);
        return this;
    }

    /**
     * Bind a form for test actions
     * @param form Target form layout
     * @return AndroidValidator instance.
     */
    public AndroidValidator bind(ViewGroup form){
        this.form = form;
        return this;
    }

    /**
     * Apply InputType to EditText.
     * @return AndroidValidator instance.
     */
    public AndroidValidator applyInputType(){
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
                        inputType = InputType.TYPE_CLASS_PHONE;
                    }else if (r instanceof CreditCardRunner || r instanceof NumericRunner){
                        inputType = InputType.TYPE_CLASS_NUMBER;
                    }else if (r instanceof DigitsRunner){
                        inputType = InputType.TYPE_NUMBER_FLAG_SIGNED;
                    }else if (r instanceof EmailRunner){
                        inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
                        item.setSingleLine(true);
                    }else if (r instanceof HostRunner || r instanceof HTTPURLRunner || r instanceof IPv4Runner){
                        inputType = InputType.TYPE_TEXT_VARIATION_URI;
                    }else if (r instanceof LengthInMaxRunner){
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
     * Set all fields `single line`
     * @return AndroidValidator instance.
     */
    public AndroidValidator singleLine(){
        checkBindForm();
        int childrenCount = form.getChildCount();
        for (int i = 0; i < childrenCount; i++){
            View c = form.getChildAt(i);
            if (c instanceof EditText){
                EditText item = (EditText) c;
                item.setSingleLine(true);
            }
        }
        return this;
    }

    /**
     * Test all fields, and get a boolean result , STOP testing when got a test failed.
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

    /**
     * Get value by view id.
     * @param viewId View Id.
     * @return String value in view.
     */
    public String getValue(int viewId){
        return values.get(viewId);
    }

    /**
     * Get value by view id from parent view.
     * @param parent Parent view
     * @param viewId view Id
     * @return String value in view.
     */
    public String getValue(View parent, int viewId){
        return ((TextView)parent.findViewById(viewId)).getText().toString();
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
     * @param field Input field, a EditText view.
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
            if ( !passed){
                if (display != null) display.show(field, message);
                break;
            }
        }
        return new ResultWrapper(passed, message, String.valueOf(input));
    }

    private void checkBindForm(){
        if (form == null) throw new IllegalStateException("Form is NULL ! Call 'bind(form)' First !");
    }
}
