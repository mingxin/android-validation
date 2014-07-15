package com.github.chenyoca.validation.runners;

import com.github.chenyoca.validation.Types;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-15
 */
public class BuildInRunners {

    public static TestRunner build(Types type){
        TestRunner runner;
        switch (type){
            case ChineseMobilePhone: runner = new ChineseMobilePhoneRunner(); break;
            case CreditCard: runner = new CreditCardRunner(); break;
            case Digits: runner = new DigitsRunner(); break;
            case Email: runner = new EmailRunner(); break;
            case EqualTo: runner = new EqualToRunner(); break;
            case Host: runner = new HostRunner(); break;
            case URL: runner = new HTTPURLRunner(); break;
            case IPv4: runner = new IPv4Runner(); break;
            case MaxLength: runner = new LengthInMaxRunner(); break;
            case MinLength: runner = new LengthInMinRunner(); break;
            case LengthInRange: runner = new LengthInRangeRunner(); break;
            case NotBlank: runner = new NotBlankRunner(); break;
            case Numeric: runner = new NumericRunner(); break;
            case Required: runner = new RequiredRunner(); break;
            case MaxValue: runner = new ValueInMaxRunner(); break;
            case MinValue: runner = new ValueInMinRunner(); break;
            case ValueInRange: runner = new ValueInRangeRunner(); break;
            default: runner = null; break;
        }
        return runner;
    }
}
