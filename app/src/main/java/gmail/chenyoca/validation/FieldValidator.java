package gmail.chenyoca.validation;


import gmail.chenyoca.validation.supported.Configuration;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-25
 * Test a field.
 */
public class FieldValidator {

    static class TestResult{

        final boolean passed;
        final String message;

        public TestResult(boolean passed, String message){
            this.passed = passed;
            this.message = message;
        }
    }

    public static TestResult test(android.widget.EditText field, Configuration conf){
        if (conf == null) return new TestResult(false,"Field test configuration CAN NOT BE NULL !!");
        field.setError("message");
        return null;
    }
}
