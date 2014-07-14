package com.github.chenyoca.validation;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-14
 */
public abstract class LazyIntLoader implements LazyValuesLoader {

    @Override
    final public double[] doubleValues() {
        return new double[0];
    }

    @Override
    final public String[] stringValues() {
        return new String[0];
    }
}
