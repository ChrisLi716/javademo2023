package com.chris.design.pattern.state;

public abstract class LiftState {

    public abstract void open();

    public abstract void close();

    public abstract void run();

    public abstract void stop();

    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

}
