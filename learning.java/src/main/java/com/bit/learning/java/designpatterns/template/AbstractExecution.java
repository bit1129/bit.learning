package com.bit.learning.java.designpatterns.template;

/**
 * Created by yuzt on 16-3-15.
 */
public class AbstractExecution {
    public void preExecution() {

    }

    public void postExecution() {

    }

    public void doExecute() {

    }

    public final void execute() {
        preExecution();
        doExecute();
        postExecution();
    }
}
