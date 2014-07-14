package com.github.chenyoca.validation;

import com.github.chenyoca.validation.supported.ChineseMobilePhoneRunner;
import com.github.chenyoca.validation.supported.CreditCardRunner;
import com.github.chenyoca.validation.supported.DigitsRunner;
import com.github.chenyoca.validation.supported.EmailRunner;
import com.github.chenyoca.validation.supported.EqualToRunner;
import com.github.chenyoca.validation.supported.HTTPURLRunner;
import com.github.chenyoca.validation.supported.HostRunner;
import com.github.chenyoca.validation.supported.IPv4Runner;
import com.github.chenyoca.validation.supported.LengthInMaxRunner;
import com.github.chenyoca.validation.supported.LengthInMinRunner;
import com.github.chenyoca.validation.supported.LengthInRangeRunner;
import com.github.chenyoca.validation.supported.NotBlankRunner;
import com.github.chenyoca.validation.supported.NumericRunner;
import com.github.chenyoca.validation.supported.RequiredRunner;
import com.github.chenyoca.validation.supported.TestRunner;
import com.github.chenyoca.validation.supported.ValueInMaxRunner;
import com.github.chenyoca.validation.supported.ValueInMinRunner;
import com.github.chenyoca.validation.supported.ValueInRangeRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-25
 * Test configuration
 */
public class Config {

    public final List<TestRunner> runners = new ArrayList<TestRunner>();

    /**
     * Make a configuration from build in types .
     * @param type Build in type .
     * @return Configuration instance .
     */
    public static Config from(Types type){
        return from(null, type, 0, 0);
    }

    /**
     * Make a configuration from build in types with message.
     * @param message Message for validate failed.
     * @param type Build in type .
     * @return Configuration instance .
     */
    public static Config from(String message, Types type){
        return from(message, type, 0, 0);
    }

    /**
     * Make a configuration from build in types with values.
     * @param message Message for validate failed.
     * @param type Build in type .
     * @param values Extra values for validate runner parameters.
     * @return Configuration instance .
     */
    public static Config from(String message, Types type, int... values){
        return new Config().add(message, type, values);
    }

    /**
     * Make a configuration from build in types with values.
     * @param type Build in type .
     * @param values Extra values for validate runner parameters.
     * @return Configuration instance .
     */
    public static Config from(Types type, int... values){
        return from(null, type, values);
    }

    /**
     * Make a configuration from build in types with values.
     * @param type Build in type .
     * @param values Extra values for validate runner parameters.
     * @return Configuration instance .
     */
    public static Config from(Types type, double... values){
        return from(null, type, values);
    }


    /**
     * Make a configuration from build in types with values.
     * @param message Message for validate failed.
     * @param type Build in type .
     * @param values Extra values for validate runner parameters.
     * @return Configuration instance .
     */
    public static Config from(String message, Types type, double... values){
        return new Config().add( message, type, values);
    }

    /**
     * Make a configuration from build in types with values.
     * @param message Message for validate failed.
     * @param type Build in type .
     * @param values Extra values for validate runner parameters.
     * @return Configuration instance .
     */
    public static Config from(String message, Types type, String... values){
        return new Config().add(message, type, values);
    }

    /**
     * Make a configuration from build in types with values.
     * @param type Build in type .
     * @param values Extra values for validate runner parameters.
     * @return Configuration instance .
     */
    public static Config from(Types type, String... values){
        return from(null, type, values);
    }

    /**
     * Add a custom test runner.
     * @param customTestRunner Custom test runner impl.
     * @return Configuration instance.
     */
    public Config add(TestRunner customTestRunner){
        if (customTestRunner != null){
            runners.add(customTestRunner);
        }
        return this;
    }

    /**
     * Add a configuration by type
     * @param type Build in type .
     * @return Configuration instance
     */
    public Config add(Types type){
        return add(null, type, 0, 0);
    }

    /**
     * Add a configuration by type with message
     * @param type Build in type .
     * @return Configuration instance
     */
    public Config add(String message, Types type){
        return add( message, type,0, 0);
    }

    /**
     * Add a configuration by type with Int values
     * @param type Build in type .
     * @return Configuration instance
     */
    public Config add(Types type, int... intValues){
        return add( null,type, intValues);
    }

    /**
     * Add a configuration by type with Double values
     * @param type Build in type .
     * @return Configuration instance
     */
    public Config add(Types type, double... intValues){
        return add( null,type, intValues);
    }

    /**
     * Add a configuration by type with String values
     * @param type Build in type .
     * @return Configuration instance
     */
    public Config add(Types type, String... intValues){
        return add( null,type, intValues);
    }

    /**
     * Add a configuration by type with message and Int values
     * @param type Build in type .
     * @return Configuration instance
     */
    public Config add(String message, Types type, int... intValues){
        TestRunner r = runner(type);
        r.setParameters(message, intValues);
        return this;
    }

    /**
     * Add a configuration by type with message and String values
     * @param message Message for validate failed.
     * @param type Build in type
     * @param strValues String values
     * @return Configuration instance
     */
    public Config add(String message, Types type, String... strValues){
        TestRunner r = runner(type);
        r.setParameters(message, strValues);
        return this;
    }

    /**
     * Add a configuration by type with message and Double values
     * @param message Message for validate failed.
     * @param type Build in type
     * @param dValues Double values
     * @return Configuration instance
     */
    public Config add(String message, Types type, double... dValues){
        TestRunner r = runner(type);
        r.setParameters(message, dValues);
        return this;
    }

    private TestRunner runner(Types type){
        TestRunner r;
        switch (type){
            case ChineseMobilePhone: r = new ChineseMobilePhoneRunner(); break;
            case CreditCard: r = new CreditCardRunner(); break;
            case Digits: r = new DigitsRunner(); break;
            case Email: r = new EmailRunner(); break;
            case EqualTo: r = new EqualToRunner(); break;
            case Host: r = new HostRunner(); break;
            case HTTPURL: r = new HTTPURLRunner(); break;
            case IPv4: r = new IPv4Runner(); break;
            case LengthInMax: r = new LengthInMaxRunner(); break;
            case LengthInMin: r = new LengthInMinRunner(); break;
            case LengthInRange: r = new LengthInRangeRunner(); break;
            case NotBlank: r = new NotBlankRunner(); break;
            case Numeric: r = new NumericRunner(); break;
            case Required: r = new RequiredRunner(); break;
            case ValueInMax: r = new ValueInMaxRunner(); break;
            case ValueInMin: r = new ValueInMinRunner(); break;
            case ValueInRange: r = new ValueInRangeRunner(); break;
            default: r = null; break;
        }
        if (Types.Required.equals(type)){
            runners.add(0,r);
        }else{
            runners.add(r);
        }
        return r;
    }


}
