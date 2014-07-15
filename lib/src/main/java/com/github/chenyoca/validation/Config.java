package com.github.chenyoca.validation;

import com.github.chenyoca.validation.runners.BuildInRunners;
import com.github.chenyoca.validation.runners.TestRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-25
 * Test configuration
 */
public class Config {

    public final List<TestRunner> runners = new ArrayList<TestRunner>();

    private TestRunner lastRunner;
    private Types lastType;

    private Config(){}

    /**
     * Build a config by type
     * @param type Build in type
     * @return Config instance
     */
    public static Config build(Types type){
        Config c = new Config();
        c.add(type);
        return c;
    }

    /**
     * Build a config by a custom test runner
     * @param runner Custom runner
     * @return Config instance
     */
    public static Config build(TestRunner runner){
        Config c = new Config();
        c.add(runner);
        return c;
    }

    /**
     * Add a custom runner to config
     * @param runner Custom runner
     * @return Config instance
     */
    public Config add(TestRunner runner){
        autoCommit();
        lastRunner = runner;
        return this;
    }

    public Config add(Types type){
        lastType = type;
        autoCommit();
        lastRunner = BuildInRunners.build(type);
        return this;
    }

    public Config message(String message){
        lastRunner.setValues(message, null, null);
        return this;
    }

    public Config loader(LazyLoader loader){
        lastRunner.setLazyLoader(loader);
        return this;
    }

    public Config values(int ...values){
        lastRunner.setValues(values);
        return this;
    }

    public Config values(double ...values){
        lastRunner.setValues(values);
        return this;
    }

    public Config values(String ...values){
        lastRunner.setValues(values);
        return this;
    }

    public Config apply(){
        if (Types.Required.equals(lastType)){
            runners.add(0,lastRunner);
        }else{
            runners.add(lastRunner);
        }
        lastRunner = null;
        return this;
    }

    private void autoCommit(){
        if (lastRunner != null) apply();
    }

}
