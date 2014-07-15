package com.github.chenyoca.validation.supports;

import com.github.chenyoca.validation.LazyLoader;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-14
 */
public abstract class DoubleLazyLoader implements LazyLoader {

    @Override
    final public int[] intValues() {
        return new int[0];
    }

    @Override
    final public String[] stringValues() {
        return new String[0];
    }
}
