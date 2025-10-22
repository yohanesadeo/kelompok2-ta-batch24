package com.juaracoding.dikahadir.pages.actions;

public interface CompositeAction {
    void execute();
    boolean isCompleted();
    String getResult();
}
