package com.github.chenyoca.validation;

public class ResultWrapper {

    public final boolean passed;
    public final String message;
    final String value;

    public ResultWrapper(boolean passed, String message, String value){
        this.passed = passed;
        this.message = message;
        this.value = value;
    }
}