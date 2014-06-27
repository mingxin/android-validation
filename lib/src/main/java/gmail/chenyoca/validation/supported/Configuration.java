package gmail.chenyoca.validation.supported;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import gmail.chenyoca.validation.supported.runners.ChineseMobilePhoneRunner;
import gmail.chenyoca.validation.supported.runners.CreditCardRunner;
import gmail.chenyoca.validation.supported.runners.DigitsRunner;
import gmail.chenyoca.validation.supported.runners.EmailRunner;
import gmail.chenyoca.validation.supported.runners.EqualToRunner;
import gmail.chenyoca.validation.supported.runners.HTTPURLRunner;
import gmail.chenyoca.validation.supported.runners.HostRunner;
import gmail.chenyoca.validation.supported.runners.IPv4Runner;
import gmail.chenyoca.validation.supported.runners.LengthInMaxRunner;
import gmail.chenyoca.validation.supported.runners.LengthInMinRunner;
import gmail.chenyoca.validation.supported.runners.LengthInRangeRunner;
import gmail.chenyoca.validation.supported.runners.NotBlankRunner;
import gmail.chenyoca.validation.supported.runners.NumericRunner;
import gmail.chenyoca.validation.supported.runners.RequiredRunner;
import gmail.chenyoca.validation.supported.runners.ValueInMaxRunner;
import gmail.chenyoca.validation.supported.runners.ValueInMinRunner;
import gmail.chenyoca.validation.supported.runners.ValueInRangeRunner;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-25
 * Test configuration
 */
public class Configuration {

    public final List<TestRunner> runners = new ArrayList<TestRunner>();

    private Context resContext;

    private Configuration(Context c) {
        this.resContext = c;
    }

    /**
     * Make a configuration from build in types .
     * @param type Build in type .
     * @return Configuration instance .
     */
    public static Configuration buildIn(Context c, BuildInTypes type){
        return buildIn(c, null, type, 0, 0);
    }

    /**
     * Make a configuration from build in types with message.
     * @param message Message for validate failed.
     * @param type Build in type .
     * @return Configuration instance .
     */
    public static Configuration buildIn(Context c, String message, BuildInTypes type){
        return buildIn(c, message, type, 0, 0);
    }

    /**
     * Make a configuration from build in types with values.
     * @param message Message for validate failed.
     * @param type Build in type .
     * @param values Extra values for validate runner parameters.
     * @return Configuration instance .
     */
    public static Configuration buildIn(Context c, String message, BuildInTypes type, int... values){
        return new Configuration(c).add(message, type, values);
    }

    /**
     * Make a configuration from build in types with values.
     * @param type Build in type .
     * @param values Extra values for validate runner parameters.
     * @return Configuration instance .
     */
    public static Configuration buildIn(Context c, BuildInTypes type, int... values){
        return buildIn(c, null, type, values);
    }

    /**
     * Make a configuration from build in types with values.
     * @param type Build in type .
     * @param values Extra values for validate runner parameters.
     * @return Configuration instance .
     */
    public static Configuration buildIn(Context c, BuildInTypes type, double... values){
        return buildIn(c, null, type, values);
    }

    /**
     * Make a configuration from build in types with values.
     * @param message Message for validate failed.
     * @param type Build in type .
     * @param values Extra values for validate runner parameters.
     * @return Configuration instance .
     */
    public static Configuration buildIn(Context c, String message, BuildInTypes type, double... values){
        return new Configuration(c).add( message, type, values);
    }

    /**
     * Make a configuration from build in types with values.
     * @param message Message for validate failed.
     * @param type Build in type .
     * @param values Extra values for validate runner parameters.
     * @return Configuration instance .
     */
    public static Configuration buildIn(Context c, String message, BuildInTypes type, String... values){
        return new Configuration(c).add(message, type, values);
    }

    /**
     * Make a configuration from build in types with values.
     * @param type Build in type .
     * @param values Extra values for validate runner parameters.
     * @return Configuration instance .
     */
    public static Configuration buildIn(Context c, BuildInTypes type, String... values){
        return buildIn(c, null, type, values);
    }

    /**
     * Make a configuration from custom test runner.
     * @param customTestRunner Custom test runner impl.
     * @return Configuration instance
     */
    public static Configuration custom(Context c, TestRunner customTestRunner){
        if (customTestRunner == null) throw new IllegalArgumentException("Test Runner CANNOT BE NULL !!");
        return new Configuration(c).add(customTestRunner);
    }

    /**
     * Add a custom test runner.
     * @param customTestRunner Custom test runner impl.
     * @return Configuration instance.
     */
    public Configuration add(TestRunner customTestRunner){
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
    public Configuration add(BuildInTypes type){
        return add(null, type, 0, 0);
    }

    /**
     * Add a configuration by type with message
     * @param type Build in type .
     * @return Configuration instance
     */
    public Configuration add(String message, BuildInTypes type){
        return add( message, type,0, 0);
    }

    /**
     * Add a configuration by type with Int values
     * @param type Build in type .
     * @return Configuration instance
     */
    public Configuration add(BuildInTypes type, int... intValues){
        return add( null,type, intValues);
    }

    /**
     * Add a configuration by type with Double values
     * @param type Build in type .
     * @return Configuration instance
     */
    public Configuration add(BuildInTypes type, double... intValues){
        return add( null,type, intValues);
    }

    /**
     * Add a configuration by type with String values
     * @param type Build in type .
     * @return Configuration instance
     */
    public Configuration add(BuildInTypes type, String... intValues){
        return add( null,type, intValues);
    }

    /**
     * Add a configuration by type with message and Int values
     * @param type Build in type .
     * @return Configuration instance
     */
    public Configuration add(String message, BuildInTypes type, int... intValues){
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
    public Configuration add(String message, BuildInTypes type, String... strValues){
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
    public Configuration add(String message, BuildInTypes type, double... dValues){
        TestRunner r = runner(type);
        r.setParameters(message, dValues);
        return this;
    }

    private TestRunner runner(BuildInTypes type){
        TestRunner r;
        switch (type){
            case ChineseMobilePhone: r = new ChineseMobilePhoneRunner(resContext); break;
            case CreditCard: r = new CreditCardRunner(resContext); break;
            case Digits: r = new DigitsRunner(resContext); break;
            case Email: r = new EmailRunner(resContext); break;
            case EqualTo: r = new EqualToRunner(resContext); break;
            case Host: r = new HostRunner(resContext); break;
            case HTTPURL: r = new HTTPURLRunner(resContext); break;
            case IPv4: r = new IPv4Runner(resContext); break;
            case LengthInMax: r = new LengthInMaxRunner(resContext); break;
            case LengthInMin: r = new LengthInMinRunner(resContext); break;
            case LengthInRange: r = new LengthInRangeRunner(resContext); break;
            case NotBlank: r = new NotBlankRunner(resContext); break;
            case Numeric: r = new NumericRunner(resContext); break;
            case Required: r = new RequiredRunner(resContext); break;
            case ValueInMax: r = new ValueInMaxRunner(resContext); break;
            case ValueInMin: r = new ValueInMinRunner(resContext); break;
            case ValueInRange: r = new ValueInRangeRunner(resContext); break;
            default: r = null; break;
        }
        if (BuildInTypes.Required.equals(type)){
            runners.add(0,r);
        }else{
            runners.add(r);
        }
        return r;
    }


}
