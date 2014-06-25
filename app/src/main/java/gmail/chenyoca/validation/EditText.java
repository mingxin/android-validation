package gmail.chenyoca.validation;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import gmail.chenyoca.validation.supported.Configuration;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-25
 * Testable EditTextView
 */
public class EditText extends android.widget.EditText {

    Configuration testConf;

    public EditText(Context context) {
        super(context);
    }

    public EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.validation);
        Boolean required = typedArray.getBoolean(R.styleable.validation_required, false);
        String message = typedArray.getString(R.styleable.validation_required_msg);

        System.out.println(">> IS REQUIRED: " + required);
        System.out.println(">> ERR_MSG: " + message);

    }
}
