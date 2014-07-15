package com.github.chenyoca.validation.runners;

import com.github.chenyoca.validation.LazyLoader;

import java.util.regex.Pattern;

/**
 * AUTH: chenyoca (Yoojia.Chen@gmail.com)
 * DATE: 2014-06-25
 * Test runner.
 */
public abstract class TestRunner {

    protected static final int USING_INTEGER_VALUES = 0;
    protected static final int USING_STRING_VALUES = 1;
    protected static final int USING_DOUBLE_VALUES = 2;

    protected int usingValuesType = -1;

    protected String message;

    protected double dValue1 = 0;
    protected double dValue2 = 0;

    public int iValue1 = 0;
    public int iValue2 = 0;

    protected String sValue1 = null;
    protected String sValue2 = null;

    private LazyLoader lazyLoader;

    public TestRunner(String message){
        this.message = message;
    }

    public abstract boolean test(CharSequence inputValue);

    protected boolean dispatch(CharSequence inputValue){
        performLazyLoader();
        switch (usingValuesType){
            case USING_INTEGER_VALUES:
                if (message != null) message = String.format(message, iValue1, iValue2);
                int iInputValue = 0;
                try{
                    iInputValue = Integer.valueOf(inputValue.toString());
                }catch (Exception e){
                    message = e.getMessage();
                }
                return testIntValue(iInputValue, iValue1,iValue2);

            case USING_DOUBLE_VALUES:
                if (message != null) message = String.format(message, dValue1, dValue2);
                double dInputValue = 0;
                try{
                    dInputValue = Double.valueOf(inputValue.toString());
                }catch (Exception e){
                    message = e.getMessage();
                }
                return testDoubleValue(dInputValue, dValue1, dValue2);

            case USING_STRING_VALUES:
                if (message != null) message = String.format(message, sValue1, sValue2);
                return testStringValue(String.valueOf(inputValue), sValue1, sValue2);

            default: return false;
        }
    }

    private void performLazyLoader(){
        if (lazyLoader != null){
            int[] iVs = lazyLoader.intValues();
            double[] dVs = lazyLoader.doubleValues();
            String[] sVs = lazyLoader.stringValues();

            if (iVs.length != 0) {
                usingValuesType = USING_INTEGER_VALUES;
                if (iVs.length == 2){
                    iValue1 = iVs[0];
                    iValue2 = iVs[1];
                }else if (iVs.length == 1){
                    iValue1 = iVs[0];
                }
            }

            if (dVs.length != 0){
                usingValuesType = USING_DOUBLE_VALUES;
                if (dVs.length == 2){
                    dValue1 = dVs[0];
                    dValue2 = dVs[1];
                }else if (dVs.length == 1){
                    dValue1 = dVs[0];
                }
            }

            if (sVs.length != 0){
                usingValuesType = USING_STRING_VALUES;
                if (sVs.length == 2){
                    sValue1 = sVs[0];
                    sValue1 = sVs[1];
                }else if (sVs.length == 1){
                    sValue1 = sVs[0];
                }
            }

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
        return message == null? "" : message;
    }

    public void setLazyLoader(LazyLoader lazyloader){
        this.lazyLoader = lazyloader;
    }

    public void setMessage(String message){
        if (message != null) this.message = message;
    }

    public void setValues(int... values){
        usingValuesType = USING_INTEGER_VALUES;
        if (values.length > 0){
            if ( 1 == values.length){
                this.iValue1 = values[0];
            }else if ( 2 == values.length){
                this.iValue1 = values[0];
                this.iValue2 = values[1];
            }
        }
    }

    public void setValues(String... values){
        usingValuesType = USING_STRING_VALUES;
        if (values.length > 0){
            if ( 1 == values.length){
                this.sValue1 = values[0];
            }else if ( 2 == values.length){
                this.sValue1 = values[0];
                this.sValue2 = values[1];
            }
        }
    }

    public void setValues(double... values){
        usingValuesType = USING_DOUBLE_VALUES;
        if (values.length > 0){
            if ( 1 == values.length){
                this.dValue1 = values[0];
            }else if ( 2 == values.length){
                this.dValue1 = values[0];
                this.dValue2 = values[1];
            }
        }
    }
}
