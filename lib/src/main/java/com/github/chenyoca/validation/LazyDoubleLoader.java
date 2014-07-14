package com.github.chenyoca.validation;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-14
 */
public abstract class LazyDoubleLoader implements LazyValuesLoader {

    @Override
    final public int[] intValues() {
        return new int[0];
    }

    @Override
    final public String[] stringValues() {
        return new String[0];
    }
}
