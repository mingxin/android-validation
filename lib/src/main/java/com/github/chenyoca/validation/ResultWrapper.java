package com.github.chenyoca.validation;

public class ResultWrapper {

    public final boolean passed;
    public final String message;

    public ResultWrapper(boolean passed, String message){
        this.passed = passed;
        this.message = message;
    }
}