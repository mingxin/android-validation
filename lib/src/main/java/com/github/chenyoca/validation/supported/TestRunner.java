package com.github.chenyoca.validation.supported;

import java.util.regex.Pattern;

/**
 * AUTH: chenyoca (chenyoca@gmail.com)
 * DATE: 2014-06-25
 * Test runner.
 */
public abstract class TestRunner {

    protected static final int USING_INT = 0;
    protected static final int USING_STR = 1;
    protected static final int USING_DOU = 2;

    protected int usingType = -1;

    protected String message;

    protected double dValue1 = 0;
    protected double dValue2 = 0;

    public int iValue1 = 0;
    public int iValue2 = 0;

    protected String sValue1 = null;
    protected String sValue2 = null;

    public TestRunner(String message){
        this.message = message;
    }

    public abstract boolean test(CharSequence inputValue);

    protected boolean dispatch(CharSequence inputValue){
        switch (usingType){
            case USING_INT:
                if (message != null) message = String.format(message, iValue1, iValue2);
                int iInputValue = 0;
                try{
                    iInputValue = Integer.valueOf(inputValue.toString());
                }catch (Exception e){
                    message = e.getMessage();
                }
                return testIntValue(iInputValue, iValue1,iValue2);
            case USING_DOU:
                if (message != null) message = String.format(message, dValue1, dValue2);
                double dInputValue = 0;
                try{
                    dInputValue = Double.valueOf(inputValue.toString());
                }catch (Exception e){
                    message = e.getMessage();
                }
                return testDoubleValue(dInputValue, dValue1, dValue2);
            case USING_STR:
                if (message != null) message = String.format(message, sValue1, sValue2);
                return testStringValue(String.valueOf(inputValue), sValue1, sValue2);
            default: return false;
        }
    }

    protected boolean testIntValue(int inputValue, int val1, int val2){ return false; }
    protected boolean testDoubleValue(double inputValue, double val1, double val2){ return false; }
    protected boolean testStringValue(String inputValue, String val1, String bal2){ return false; }

    protected static boolean match(String regex, CharSequence inputValue){
        Pattern p = Pattern.compile(regex);
        return p.matcher(inputValue).matches();
    }

    public String getMessage(){
        if (message == null) return "";
        return message;
    }

    public void setParameters(String message, int... values){
        usingType = USING_INT;
        if (message != null) this.message = message;
        if (values != null){
            if ( 1 == values.length){
                this.iValue1 = values[0];
            }else if ( 2 == values.length){
                this.iValue1 = values[0];
                this.iValue2 = values[1];
            }
        }
    }

    public void setParameters(String message, String... values){
        usingType = USING_STR;
        if (message != null) this.message = message;
        if (values != null){
            if ( 1 == values.length){
                this.sValue1 = values[0];
            }else if ( 2 == values.length){
                this.sValue1 = values[0];
                this.sValue2 = values[1];
            }
        }
    }

    public void setParameters(String message, double... values){
        usingType = USING_DOU;
        if (message != null) this.message = message;
        if (values != null){
            if ( 1 == values.length){
                this.dValue1 = values[0];
            }else if ( 2 == values.length){
                this.dValue1 = values[0];
                this.dValue2 = values[1];
            }
        }
    }
}
