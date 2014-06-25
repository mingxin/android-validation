package gmail.chenyoca.validation.supported;

import gmail.chenyoca.validation.EditText;
import gmail.chenyoca.validation.R;
import gmail.chenyoca.validation.supported.runners.NotBlankRunner;
import gmail.chenyoca.validation.supported.runners.RequiredRunner;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-25
 * Test configuration
 */
public class Configuration {

    public final TestRunner runner;

    public String message;
    public double value1 = 0;
    public double value2 = 0;

    public Configuration message(String errorMsg){
        this.message = errorMsg;
        return this;
    }

    public Configuration value1(double val){
        this.value1 = val;
        return this;
    }

    public Configuration value2(double val){
        this.value2 = val;
        return this;
    }

    private Configuration(TestRunner runner) {
        this.runner = runner;
    }

    public static Configuration typeOf(Types type){
        TestRunner r = null;
        switch (type){
            case Requested: r = new RequiredRunner(); break;
            case NotBlank: r = new NotBlankRunner(); break;
        }
        return new Configuration(r);
    }

    public static Configuration configFrom(EditText field){
        if (field.typedArray == null) return null;

//        Boolean required = field.typedArray.getBoolean(R.styleable.validation_required, false);
//        String message = field.typedArray.getString(R.styleable.validation_required_msg);
//
//        System.out.println(">> IS REQUIRED: " + required);
//        System.out.println(">> ERR_MSG: " + message);

        return null;
    }
}
